#include "Arduino.h"
#include "NetworkUtils.h"
#include <HTTPClient.h>
#include <WiFi.h>

boolean NetworkUtils::sendDataRequest(String body){
  if(WiFi.status() != WL_CONNECTED) return false;

  HTTPClient http;
  String serverPath = serverName + "/";

  http.begin(serverPath);
  http.addHeader("Content-Type", "application/json");
    
  Serial.print("Post request com body: ");
  Serial.println(body);
  int httpResponseCode = http.POST(body);
  
  if(httpResponseCode == 200){
    return true;
  }
  return false;
}

int NetworkUtils::getDateTimeFromServer(){
  if(WiFi.status() != WL_CONNECTED) return 0;

  HTTPClient http;
  String serverPath = serverName + "/now";

  http.begin(serverPath);
  http.addHeader("Content-Type", "application/json");
    
  int httpResponseCode = http.GET();

  Serial.println(httpResponseCode);
  if(httpResponseCode>0){
    String payload = http.getString();
    Serial.print("getDateTimeFromServer : ");
    Serial.println(payload);
    return payload.toInt();
  }
  
  return 0;
}

boolean NetworkUtils::connectWifi(char* ssid, char* pass){
  if(WiFi.status() == WL_CONNECTED) WiFi.disconnect();
  delay(100);
  Serial.println(ssid);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, pass);
  delay(10000);  
  
  if(WiFi.status() == WL_CONNECTED){
    Serial.print("Conectado a rede: ");
    Serial.println(ssid);
    return true;
  }
  Serial.println("Falha ao conectar com rede");
  return false;
}
