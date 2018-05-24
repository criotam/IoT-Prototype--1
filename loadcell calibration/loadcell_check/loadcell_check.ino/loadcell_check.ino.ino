

#include "HX711.h"

#define DOUT  3
#define CLK  2

HX711 scale(DOUT, CLK);

float calibration_factor = -3150;
void setup() {
  Serial.begin(9600);

  scale.set_scale();
  scale.tare();  //Reset the scale to 0

  
}

void loop() {

  scale.set_scale(calibration_factor);

  Serial.print("Reading: ");
  Serial.print(scale.get_units()*0.453592, 1);
  Serial.print(" kgs");
  Serial.println();

}
