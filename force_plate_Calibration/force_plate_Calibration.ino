#define dt1 3
#define sck1 2
#define dt2 4
#define sck2 5
#define dt3 6
#define sck3 7





#include "HX711.h"


HX711 scale1(dt1 ,sck1 );
HX711 scale2(dt2 ,sck2 );
HX711 scale3(dt3 ,sck3 );


float calibration_factor = -7050; //-7050 worked for my 440lb max scale setup

void setup() {
  Serial.begin(9600);
  Serial.println("HX711 calibration sketch");
  Serial.println("Remove all weight from scale");
  Serial.println("After readings begin, place known weight on scale");
  Serial.println("Press + or a to increase calibration factor");
  Serial.println("Press - or z to decrease calibration factor");

  scale1.set_scale();
  scale2.set_scale();
  scale3.set_scale();
  scale1.tare();  //Reset the scale to 0
  scale2.tare();
  scale3.tare();

  long zero_factor1 = scale1.read_average(); //Get a baseline reading
  Serial.print("Zero factor: "); //This can be used to remove the need to tare the scale. Useful in permanent scale projects.
  //Serial.println(zero_factor1);
    long zero_factor2 = scale1.read_average(); //Get a baseline reading
  Serial.print("Zero factor: "); //This can be used to remove the need to tare the scale. Useful in permanent scale projects.
 // Serial.println(zero_factor2);
    long zero_factor3 = scale1.read_average(); //Get a baseline reading
  Serial.print("Zero factor: "); //This can be used to remove the need to tare the scale. Useful in permanent scale projects.
  //Serial.println(zero_factor3);
  Serial.print("\t\t\tscale1\t\t\t");
Serial.print("scale2\t\t\t");
Serial.print("scale3\t\t\t");
Serial.println();
}

void loop() {

  scale1.set_scale(calibration_factor); //Adjust to this calibration factor
  scale2.set_scale(calibration_factor); //Adjust to this calibration factor
  scale3.set_scale(calibration_factor); //Adjust to this calibration factor

 Serial.print("Reading: \t\t");
  Serial.print(scale1.get_units(), 1);
  Serial.print(" lbs\t\t\t"); //Change this to kg and re-adjust the calibration factor if you follow SI units like a sane person
   Serial.print(scale2.get_units(), 1);
  Serial.print(" lbs\t\t\t"); //Change this to kg and re-adjust the calibration factor if you follow SI units like a sane person
   Serial.print(scale3.get_units(), 1);
  Serial.print(" lbs\t\t\t"); //Change this to kg and re-adjust the calibration factor if you follow SI units like a sane person
  Serial.print(" calibration_factor: ");
  Serial.println();

  if(Serial.available())
  {
    char temp = Serial.read();
    if(temp == '+' || temp == 'a')
      calibration_factor += 10;
    else if(temp == '-' || temp == 'z')
      calibration_factor -= 10;
  }
}
