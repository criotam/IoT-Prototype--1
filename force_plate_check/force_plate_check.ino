

#include "HX711.h"

#define dt1 3
#define sck1 2
#define dt2 4
#define sck2 5
#define dt3 6
#define sck3 7


HX711 scale1(dt1,sck1);
HX711 scale2(dt1,sck1);
HX711 scale3(dt1,sck1);

float calibration_factor = -7050;
void setup() {
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

void loop() {

  scale1.set_scale(calibration_factor);
    scale2.set_scale(calibration_factor);
      scale3.set_scale(calibration_factor);

  Serial.print("Reading: \t\t");
  Serial.print(scale1.get_units(), 1);
  Serial.print(" kgs\t\t\t");
  /*Serial.print(scale2.get_units(), 1);
  Serial.print(" kgs\t\t\t");
  Serial.print(scale3.get_units(), 1);
  Serial.print(" kgs\t\t\t");
  */
  Serial.println();

}
