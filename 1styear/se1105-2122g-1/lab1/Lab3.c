#include <stdio.h>

 //You are selling 3 different coffee Latte(1),Mocha(2) and Ice Chocolate Mocha(3)
 //There are 3 different size for your coffees Tall(1),Grande(2) and Vetti(3)
 //Base prices for coffees are 2 TL, 4 TL and 5 TL
 //Based on size, prices are multiplied by 1, 2 or 3
 //If customer want to add sugar, it costs 1 TL
 //Ask user to what would he/she want to buy, with the choice of size and if he/she wants sugar?
 //P.S : User will only enter the valid inputs

void welcome(){
	//Print a welcome message to the user

}
 
int coffee_type(){
	//Ask user to select a coffee type

}

int coffee_size(){
	//Ask user to select a coffee size

}

int coffee_sugar(){
	//Ask if user would like to add sugar

}

void cost(int coffeeType, int coffeeSize, int sugar){
	//Print the price of the coffee acording to user's choices.
	
}

void goodbye(){
	//Print a goodbye message for the user
	
}

void main(){
	//Welcome the user, take their order, calculate the price and say goodbye
	int coffee;
	int size;
	int sugar;
	
	welcome();
	coffee = coffee_type();
	size = coffee_size();
	sugar = coffee_sugar();
	cost(coffee,size,sugar);
	goodbye();
	
}






