
#include "HX711.h"

#define dt1 2
#define sck1 3
#define dt2 4
#define sck2 5
#define dt3 6
#define sck3 7
float lc1;
float lc2;
float lc3;
HX711 scale1(dt1,sck1);
HX711 scale2(dt2,sck2);
HX711 scale3(dt3,sck3);

float calibration_factor1 = -26836;   /*-24474    -39600*/
float calibration_factor2 = 14123;    /*17371  6300*/
float calibration_factor3 = -8282;    /*-8448  -7800*/

int LED = 13;

void setup() {
  
  pinMode(LED,OUTPUT);
  digitalWrite(LED,HIGH);
  
  Serial.begin(9600);

  scale1.set_scale();
  scale1.tare();  //Reset the scale to 0
    scale2.set_scale();
  scale2.tare();
    scale3.set_scale();
  scale3.tare();
Serial.print("\t\t\tscale1\t\t\t");
Serial.print("scale2\t\t\t");
Serial.print("scale3\t\t\t");
Serial.println();
  
}
union cvt{
  byte b[4];
  float f;
  };
  
void loop() {

  digitalWrite(LED,LOW);
  cvt c;

  scale1.set_scale(calibration_factor1);
  scale2.set_scale(calibration_factor2);
  scale3.set_scale(calibration_factor3);
  lc1=scale1.get_units();
  lc2=scale2.get_units();
  lc3=scale3.get_units();
  
  //Serial.print("Reading: \t\t");
  //Serial.print(lc1);
  
  Serial.write("f");
  Serial.write(":");

  c.f = lc1;
  Serial.write(c.b,4);
  
  //Serial.print(" kgs\t\t\t");
  //Serial.print(lc2);
  c.f = lc2;
  Serial.write(c.b,4);
  
  //Serial.print(" kgs\t\t\t");
  //Serial.print(lc3);
  c.f = lc3;
  Serial.write(c.b,4);
  
  //Serial.print(" kgs\t\t\t");
  
  //Serial.println();

  digitalWrite(LED,HIGH);

}
