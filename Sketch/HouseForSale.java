import java.util.*;
import java.io.*;
public class HouseForSale
{
public static void main(String [] args) throws Exception
//{
{
// Create a window
Window w = new Window(20, 30, '*');
// Draw the ground
Line ground = new Line(19, 1, 29, 0, 1,'#');
w.addShape(ground);
// Draw the post
Line post = new Line(12, 5, 6, 1, 0, '#');
w.addShape(post);
// Draw the light
Circle light = new Circle(10, 5, 2, '+');
w.addShape(light);
// Draw the house
Rectangle house = new Rectangle(8, 16, 11, 10, '=');
w.addShape(house);
// Draw the door
Rectangle door = new Rectangle(11, 19, 8, 4, '=');
w.addShape(door);
// Draw the roof
Triangle roof = new Triangle(2, 21, 6, 1, 0, '*');
w.addShape(roof);
// Display text message
Text msg = new Text(2,10, "FOR SALE", 1, 0);
w.addShape(msg);
w.display();
//}
}
}

