#include <SoftwareSerial.h>

// Define the data transmit/receive pins in Arduino

#define TxD 31

#define RxD 30

SoftwareSerial mySerial(RxD, TxD); // RX, TX for Bluetooth

void setup() {

  mySerial.begin(9600); // For Bluetooth

  Serial.begin(9600); // For the IDE monitor Tools -> Serial Monitor

}

void loop() {
 
    byte c;

    //c = mySerial.read(); // here come to heart beat rate and speed value

    Serial.print(c); // Print the character received to the IDE serial monitor
}
