#ifndef NetworkUtils_h
#define NetworkUtils_h
#include <ArduinoJson.h>
#include "Arduino.h"
#include "Adafruit_CCS811.h"
#include <Adafruit_AHTX0.h>


class NetworkUtils{

  public:
    String serverName;
    boolean connectWifi(char* ssid, char* pass);
    boolean sendDataRequest(String body);
    int getDateTimeFromServer();
};

class Sensors{

    
  public:
    Adafruit_CCS811 ccs;
    Adafruit_AHTX0 aht;
    String startSensors();
    void getCo2DataFromSensors(JsonObject sensorData, int ammount, int dela);
    void getTempUmidadeFromSensors(JsonObject sensorData);
    void setCcs811DriveMode(int driveMode);
};

class SDCard{
  private:
    int index = 1;
  public:
    SDCard();
    String readNextData();
    void deleteFirsData();
    void saveDataToSd(String body);
  
};
#endif

// Lock the variable indefinietly. ( wait for it to be accessible )
//void lockVariable(){
//    xSemaphoreTake(i2cSemaphore, portMAX_DELAY);
//}
//
//// give back the semaphore.
//void unlockVariable(){
//    xSemaphoreGive(i2cSemaphore);
//}
//
//void createSemaphore(){
//    i2cSemaphore = xSemaphoreCreateMutex();
//    xSemaphoreGive( ( i2cSemaphore) );
//}
