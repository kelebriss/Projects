
 unsigned long lastturn;
 float length = 2.070; // 26 inch wheel 
 volatile float Dist=0;
 volatile float speed;
 void setup()
 {
   Serial.begin(9600);
   attachInterrupt(0, magnet_detect, RISING);//Initialize the intterrupt pin (Arduino digital pin 2)
   lastturn = 0;
 }
 void loop()//Measure RPM
 {
   if (millis() - lastturn >80) {
     lastturn = millis();
     speed = length  / ((millis() - lastturn) / 1000) *3.6;
     Dist = Dist + length / 1000;
     Serial.println(speed,DEC);
   }
 }
 void magnet_detect()//This function is called whenever a magnet/interrupt is detected by the arduino
 {
   half_revolutions++;
   Serial.println("detect");
 }
