#include "Arduino.h"
#include "FS.h"
#include "SD.h"
#include "SPI.h"
#include "NetworkUtils.h"


SDCard::SDCard(){
  if(SD.begin()){
    Serial.println("Cartão SD detectado");
  }else{
    Serial.println("Falha ao montar SD");
  }
}

String SDCard::readNextData(){
  if(index == 0) return "";

  File file = SD.open(String("data") + index + String(".txt"));
  if(!file) return "";

  String line;

  while(file.available()){
    line.concat(file.read());
  }
  file.close();
  return line;
}

void SDCard::deleteFirsData(){
  if(SD.remove(String("data") + index + String(".txt"))){
    index--;
  }
}

void SDCard::saveDataToSd(String body){
  SD.mkdir(String("data") + index + String(".txt"));
  File file = SD.open(String("data") + index + String(".txt"));
  if(file){
    if(file.print(body)){
      index++;
      }     
    }
  }
    
  
