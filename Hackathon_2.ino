#include <SoftwareSerial.h>

// Initialize variables
const int trigPin = 3;
const int echoPin = 2;
const int ButPin = 12;

//////////////
int max_sense_time = 3000;

//////////////

void setup() {
  // Start the serial communication
  Serial.begin(9600);
  // Define the TRIG and ECHO as output and input pins
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(ButPin, INPUT);
  digitalWrite(ButPin,HIGH);
}

//////////////////////////
// replcement for distance sensor2 hit check
int get_sense2_hit() {
  if(digitalRead(ButPin)==LOW){
    delay(10);
    if (digitalRead(ButPin)== LOW){
      return 1;
    }
  }
  return 0;
}

int get_distance(){

  // Clear the trigPin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  // Set the trigPin high for 10 microseconds
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
    
  // Read the echoPin and calculate the distance
  int duration = pulseIn(echoPin, HIGH);
  int distance1 = duration * 0.034 / 2;

  return distance1;
}
//////////////////////////

void loop() {
  int distance1 = get_distance();

  int sense1_hit = 0;
  int sense2_hit = 0;
  int output = 0;

  if (distance1 < 20) {
    sense1_hit = 1;
  }

  if (get_sense2_hit() == 1){
    sense2_hit = 1;
  }

  // both sensors hit
  if ((sense1_hit == 1) && (sense2_hit == 1)){
    // multiple people at same time, gonna assume net 0 for now
    //continue;
  }

  // sensor 1 hits (Sensor 1 -> sensor 2 means someone entered)
  else if (sense1_hit == 1){
    for(int i = 0; i < max_sense_time; i++){
      if (get_sense2_hit() == 1){
        output = 1;
        Serial.println(output);
        delay(5);
        break;
      }
      delay(1);
    }
    //continue;
  }

  else if (sense2_hit == 1){
    for(int i = 0; i < max_sense_time; i++){
      if (get_distance() < 20){
        output = -1;
        Serial.println(output);
        delay(5);
        break;
      }
      delay(1);
    }
    //continue;
  }
  delay(100); // Delay between measurements
}