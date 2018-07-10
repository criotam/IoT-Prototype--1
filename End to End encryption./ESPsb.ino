#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <AESLib.h>
byte key[] = { 0x2B, 0x7E, 0x15, 0x16, 0x28, 0xAE, 0xD2, 0xA6, 0xAB, 0xF7, 0x15, 0x88, 0x09, 0xCF, 0x4F, 0x3C };

byte my_iv[N_BLOCK] = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
AESLib aesLib;
const char* ssid     = "criotam";
const char* password = "Panasonic@2018";
const char* mqtt_server = "192.168.1.14";
int timestamp;
// Use WiFiClient class to create TCP connections
WiFiClient espClient;
PubSubClient client(espClient);


// Timers auxiliar variables
long now = millis();
long lastMeasure = 0;

int flag = 0;
const int BUTTON=2;
const int LED=0  ;
int BUTTONState=0;
String macId;

//Setting up wifi connection
void setup_wifi() {
  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  digitalWrite(LED,HIGH);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(250);
    digitalWrite(LED,LOW);
    delay(250);
    Serial.print(".");
    digitalWrite(LED,HIGH);
  }
  digitalWrite(LED,HIGH);
  delay(500);
  digitalWrite(LED,LOW);
  Serial.println("");
  Serial.print("WiFi connected - ESP IP address: ");
  Serial.println(WiFi.localIP());
}

// This functions is executed when some device publishes a message to a topic that your ESP8266 is subscribed to
// Change the function below to add logic to your program, so when a device publishes a message to a topic that 
// your ESP8266 is subscribed you can actually do something
void callback(String topic, byte* message, unsigned int length) {
  Serial.print("Message arrived on topic: ");
  Serial.print(topic);
  Serial.print(". Message: ");
  String messageTemp;
  Serial.println(messageTemp);
  for (int i = 0; i < length; i++) {
    Serial.print((char)message[i]);
    messageTemp += (char)message[i];
  }
  Serial.println();


  if(topic=="Sb/start"){
      Serial.print("Sounding buzzer once.");
      if(messageTemp == "On"){
        pinMode(2,OUTPUT);
        digitalWrite(2,HIGH);
        delay(250);
        digitalWrite(2,LOW);
        delay(250);
        //Serial.println();
        pinMode(2,INPUT);
      }
  }
  Serial.println();
}


// This functions reconnects your ESP8266 to your MQTT broker
// Change the function below if you want to subscribe to more topics with your ESP8266 
void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    /*
     YOU MIGHT NEED TO CHANGE THIS LINE, IF YOU'RE HAVING PROBLEMS WITH MQTT MULTIPLE CONNECTIONS
     To change the ESP device ID, you will have to give a new name to the ESP8266.
     Here's how it looks:
       if (client.connect("ESP8266Client")) {
     You can do it like this:
       if (client.connect("ESP1_Office")) {
     Then, for the other ESP:
       if (client.connect("ESP2_Garage")) {
      That should solve your MQTT multiple connections problem
    */
    if (client.connect("ESP8266Client")) {
      Serial.println("connected");  
      // Subscribe or resubscribe to a topic
      // You can subscribe to more topics (to control more LEDs in this example)
      client.subscribe("Sb/start");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(5000);
    }
  }
}




void setup() {

  aesLib.gen_iv(my_iv);

  Serial.println("AES Key: ");
  int i;
  for (i = 0; i < sizeof(my_iv); i++)
  {
     Serial.print(my_iv[i], DEC); 
     Serial.print(",");
  }

  
  pinMode(0, OUTPUT);
  pinMode(2,INPUT);
  
  Serial.begin(9600);
  setup_wifi();
  delay(5000);
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);


}

union cvt{
  byte b[4];
  float f;
  };

  float lc1;
  float lc2;
  float lc3;
  String Lc1;
  String Lc2;
  /*  Mac Id  */
void loop() {

  cvt c;

  BUTTONState=digitalRead(BUTTON);
  if(BUTTONState==HIGH)
  {
  //digitalWrite(LED,HIGH);
  }
  else
  {
  Serial.println("OFF");
  //setup();
  //digitalWrite(LED,LOW);
  }
  if(!client.connected()) {
    reconnect();
    }
  if(!client.loop())
    client.connect("ESP8266Client");

  
  if (client.connected()) {
    if(Serial.available()){
    char inByte = Serial.read();
    Serial.println(inByte);
    if(inByte=='f'){
      Serial.read();
      byte inData[4];

    Serial.readBytes(c.b,4);
    lc1 = c.f;
    Serial.println(lc1);

    Serial.readBytes(c.b,4);
    lc2 = c.f;
    Serial.println(lc2);
    }
   }  
    
  }
String LC1, LC2;
LC1 = String(lc1);
LC2 = String(lc2);

String LC1enc = aesLib.encrypt(LC1, key, my_iv);
String LC2enc = aesLib.encrypt(LC2, key, my_iv);

char LC1Enc[50];
char LC2Enc[50];
LC1enc.toCharArray(LC1Enc, 50);
LC2enc.toCharArray(LC2Enc, 50);

static char Lc1[7];
dtostrf(lc1, 6, 2, Lc1);
static char Lc2[7];
dtostrf(lc2, 6, 2, Lc2);
  
  client.publish("StartingBlock/Lc1", LC1Enc);
  client.publish("StartingBlock/Lc2", LC2Enc);
  Serial.println("AES Key: ");
  int i;
  for (i = 0; i < sizeof(my_iv); i++)
  {
     Serial.print(my_iv[i], DEC); 
     Serial.print(",");
  }
  Serial.println("Load cell 1 : ");
  Serial.println(LC1Enc);
  Serial.println("Load cell 2 : ");
  Serial.println(LC2Enc);
  // wait to fully let the client disconnect
 delay(1000);
}
