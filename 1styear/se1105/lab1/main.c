#include <stdio.h>

 //You are selling 3 different coffee Latte(1),Mocha(2) and Ice Chocolate Mocha(3)
 //There are 3 different size for your coffees Tall(1),Grande(2) and Vetti(3)
 //Base prices for coffees are 2 TL, 4 TL and 5 TL
 //Based on size, prices are multiplied by 1, 2 or 3
 //If customer want to add sugar, it costs 1 TL
 //Ask user to what would he/she want to buy, with the choice of size and if he/she wants sugar?
 //P.S : User will only enter the valid inputs
 //I additionally added milk option for the customers, which costs 2 TL.
 //I also added coupon option, if the user provides a coupon, they get 10% discount.

void welcome() {
    // Print a welcome message to the user
    
    printf("Welcome, what would you like to buy? We are selling three different types of coffees: Latte, Mocha and Ice Chocolate Mocha.\n");	
}
 
int coffee_type() {
    // Ask user to select a coffee type
    
    int type;
    printf("Please choose which type of coffee you want. Enter 1 if you wish to choose Latte, 2 if you wish to choose Mocha and 3 if you wish to choose Ice Chocolate Mocha: ");
    scanf("%d", &type);
    return type;	
}

int coffee_size() {
    // Ask user to select a coffee size
    
    int size;
    printf("Please choose which size you want your cup to be. Enter 1 for Tall, 2 for Grande and 3 for Vetti sized cup: ");
    scanf("%d", &size);
    return size;
}

int coffee_sugar() {
    // Ask if user would like to add sugar
    
    int sugar;
    printf("Please specify if you want sugar. Enter 1 for yes and 0 for no: "); // in fact any other input than 0 will be treated as true, i.e. add sugar
    scanf("%d", &sugar); 
    return sugar;
}

int coffee_milk() {
    int milk;
    printf("Please specify if you want milk. Enter 1 for yes and 0 for no: "); // in fact any other input than 0 will be treated as true, i.e. add milk
    scanf("%d", &milk); 
    return milk;
}

int coffee_coupon() {
    int coupon;
    printf("If you have a coupon, please provide it by entering 1 and if you don't have a coupon, enter 0: "); // in fact any other input than 0 will be treated as true, i.e. provide coupon
    scanf("%d", &coupon); 
    return coupon;
}


void cost(int coffeeType, int coffeeSize, int sugar, int milk, int coupon) {
    // Print the price of the coffee acording to user's choices.
    
    float result_price = 0;
    if(coffeeType == 1) {
    	result_price += 2;
    } else if (coffeeType == 2) {
    	result_price += 4;
    } else if (coffeeType == 3) {
    	result_price += 5;
    }
    
    if (coffeeSize == 1) {
        result_price *= 1; // unnecessary for the current coefficient but the coefficient might change in the future
    } else if (coffeeSize == 2) {
        result_price *= 2;
    } else if (coffeeSize == 3) {
        result_price *= 3;
    }
    
    if(sugar) {
        result_price += 1;
    } 
    
    if(milk) {
        result_price += 2;
    }
    
    if(coupon) {
        result_price -= result_price / 10;
    }
    
    printf("Your order has been evaluated. The price is %.1f TL, enjoy your coffee!\n", result_price);
}

void goodbye() {
    // Print a goodbye message for the user
    
    printf("We hope you are satisfied with our coffee, we wish to see you here again in the future, goodbye!\n");     	
}

void main(){
    //Welcome the user, take their order, calculate the price and say goodbye
    
    int coffee;
    int size;
    int sugar;
    int milk;
    int coupon;
    	
    welcome();
    coffee = coffee_type();
    size = coffee_size();
    sugar = coffee_sugar();
    milk = coffee_milk();
    coupon = coffee_coupon();
    cost(coffee, size, sugar, milk, coupon);
    goodbye();	
}
