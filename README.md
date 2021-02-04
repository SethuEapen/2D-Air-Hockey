# 2D-Air-Hockey
Physics based Air Hockey

<p align="center">
  <img width="340" height="600" src="https://user-images.githubusercontent.com/31808277/106960817-7f127680-66f1-11eb-9216-5247c30f7d51.gif">
</p>

## Requirements
[Java JDK](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) 15 is required to run this program

## Physics

### Trigonometry
Trig was used to calculate angles of deflection. Ex:
```
//Code in Player Class
double angle = Math.atan2(dify, difx);
int newX = (int) (Math.cos(angle) * distance) + x;
int newY = (int) (Math.sin(angle) * distance) + y;
```


### Transfer Of Momentum
Momentum was numericaly calculated for collisions in a previous implementaion. A proporianal relationship is now set to enable more accurate preditions.
#### Old Implementation:
```
//Code in Player Class
double compnt;
		
double top = 2*(m1 * v1) - (m1 * v2) + (m2 * v2);
double bottom = m1 + m2;
		
compnt = top / bottom;
```
#### New Implementation:
```
//Code in Player Class
double angle = Math.atan2(dify, difx);
tempObject.velX = (int) (Math.cos(angle) * totalVel);
tempObject.velY = (int) (Math.sin(angle) * totalVel);
```

### Kinematics
Calculations for speed of puck after a hit. Ex:
```
x = x + (int) ((velX * timePassed) + (.5 * accX * Math.pow(timePassed, 2)));
velX = velX + (accX * timePassed);
y = y + (int) ((velY * timePassed) + (.5 * accY * Math.pow(timePassed, 2)));
velY = velY + (accY * timePassed);
```

### Misc.
Other implementaions of physics can be seen throughout the source code

## AI
This AI is very basic and just moves towards the ball. To balance this, I made the AI overpowered by making it super fast and allowing it to basically prematurely end the game by slamming the puck through the wall.

## Special Thanks
### to [RealTutsGML](https://www.youtube.com/channel/UCOs7Q7IeuzgRyARaEqif75A) for Java game tutorials that helped with the strucure of this code.
### and Mr. Pederson for being an amazing teacher! Im going to miss you next year!
