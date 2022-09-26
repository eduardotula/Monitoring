#include <ArduinoJson.h>
#include "NetworkUtils.h"
#include "time.h"
#include <ESP32Time.h>
#include "SD.h"

Sensors sensors;
NetworkUtils utils;
SDCard sdCard;
ESP32Time rtc(1);
int LED_BUILTIN = 2;
char* ssid = "yeet"; //SSID Wifi
char* pass = "1020304050"; //Wifi Password
//Identeificador unico
uint8_t mac[6];
//Variavel de semafaro
SemaphoreHandle_t i2cSemaphore;
int delaySendData = 10000;
int delayReciveLog = 5000;
TaskHandle_t  taskcommandListnerHandleApi;
TaskHandle_t  taskcommandListnerHandle;
boolean taskRunFlag = true;


void createSemaphore();
void unlockVariable();
void lockVariable();
void taskcommandListnerApi(void*);
void taskcommandListner(void*);
void runCommand(String command, String param);

void setup() {
  Serial.begin(115200);
  //Leitura de mac
  esp_efuse_read_mac(mac);
  Serial.printf("MAC atual :%X\n", mac);
  utils.serverName = "http://192.168.0.102:14990/esp32/data";
  while(!utils.connectWifi(ssid, pass));
  Serial.println(sensors.startSensors());

   
  rtc.setTime(utils.getDateTimeFromServer());
  //Start task
  xTaskCreatePinnedToCore(taskcommandListner, "TaskSerial", 10000, NULL, 2, &taskcommandListnerHandle,0);
  xTaskCreatePinnedToCore(taskcommandListnerApi, "TaskApi", 10000, NULL, 5, &taskcommandListnerHandleApi,0);
}

//Loop de coleta, armazenagem e envio de dados
void loop(){
  sendStoredData();
  String body;
  DynamicJsonDocument requestBody(1024);
  requestBody.garbageCollect();
  requestBody["epoch"] = rtc.getLocalEpoch();
  requestBody["identificador"] = (int) mac;

  JsonObject sensorData = requestBody.createNestedObject("sensorData");
  sensors.getCo2DataFromSensors(sensorData, 10, 5000);
  sensors.getTempUmidadeFromSensors(sensorData);
  
  serializeJson(requestBody, body);
  int response = utils.sendDataRequest(body);
  if(!response) sdCard.saveDataToSd(body);
  Serial.println(body);
  delay(delaySendData);
}

void sendStoredData(){
  String line;
  while(line = sdCard.readNextData() != ""){
    if(utils.sendDataRequest(line)) sdCard.deleteFirsData();
    else break;
    delay(1000);
  }
}

//Task comunicar parametros com API
void taskcommandListnerApi(void*){
  while(1){
    
    vTaskDelay(delayReciveLog);
  }
 vTaskDelete(NULL);
}

//Task comunicar parametros com serial
void taskcommandListner(void*){
  while(1){
    String line = Serial.readStringUntil('\n');
    int breakChar = line.indexOf('=');
    String command = line.substring(0, breakChar);
    String param = line.substring(breakChar+1, line.length());
    runCommand(command, param);
    vTaskDelay(delayReciveLog);
  }
 vTaskDelete(NULL);
}

void runCommand(String command, String param){
    if( command == "ccs.mode") {sensors.setCcs811DriveMode(param.toInt()); Serial.println("Sucesso");}
    if( command == "ccs.reset" && param == "1") {sensors.ccs.SWReset(); Serial.println("Sucesso");}
    if(command == "delayReciveLog"){ delayReciveLog = param.toInt(); Serial.println("Sucesso");}
    if(command == "delaySendData"){ delaySendData = param.toInt(); Serial.println("Sucesso");}
}
