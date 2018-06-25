
#include "HX711.h"

int dt1 = 2;
int sck1 = 3;
int dt2 =4;
int sck2 =5;
HX711 scale1(dt1, sck1);
HX711 scale2(dt2, sck2);
const int LED = 13;
float calibration_factor = -3150; //-7050 worked for my 440lb max scale setup
const int buzzer = 9;
void setup() {
  
  pinMode(LED,OUTPUT);
  digitalWrite(LED, HIGH);
  
  Serial.begin(9600);
    //Serial.println();
 //Serial.println();

  scale1.set_scale();
  scale1.tare();  //Reset the scale to 0
  scale2.set_scale();
  scale2.tare();
  //Serial.println();
  //Serial.print("\t\t\t\tscale1\t\t\t");
  //Serial.print("scale2\t\t");
  //Serial.println();
}
float lc1;
float lc2;

union cvt{
  byte b[4];
  float f;
  };
  
void loop() {
  
  /*
  if(Serial.available()){
    if(Serial.readString()=="buzzer"){
      pinMode(buzzer,OUTPUT);
      digitalWrite(buzzer,HIGH);
      delay(500);
      digitalWrite(buzzer,LOW);
    }
  }*/
  
  //digitalWrite(LED,HIGH);
  //delay(100);
  digitalWrite(LED, LOW);
  cvt c;
  scale1.set_scale(calibration_factor); //Adjust to this calibration factor
  scale2.set_scale(calibration_factor); 
 //Serial.print("Reading: \t\t");
  lc1=scale1.get_units();
  lc2=scale2.get_units();
 //Serial.print(lc1);
  //Serial.print(" lbs\t\t\t"); //Change this to kg and re-adjust the calibration factor if you follow SI units like a sane person*/
 
  
  Serial.write("f");
  Serial.write(":");

  c.f = lc1;
  Serial.write(c.b,4);
  
  //Serial.print(lc2);
  //Serial.print(" lbs\t\t\t"); 
  
  c.f = lc2;
  Serial.write(c.b,4);
  
  //Serial.print(" calibration_factor: ");
  //Serial.print(calibration_factor);
  //Serial.println();
 //digitalWrite(LED,LOW);
 //delay(100);
 digitalWrite(LED, HIGH);
 
}
