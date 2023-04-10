#include <iostream>

#define MARKET1 true
#define MARKET2 false
#define ITEM_COUNT 5

int market1Prices[] = {5, 7, 12, 3, 4};
int market2Prices[] = {8, 4, 9, 9, 6};
int market1Point = 8;
int market2Point = 18;
bool markets[ITEM_COUNT];

// Changes global variables market1Point, market2Point and markets. Could've refactored it to return them as a Solution struct but I didn't want to leave the scope of the assignment.
// Also I noticed this doesn't always solve the problem with the optimal solution as the problem doesn't have a optimal substructure.
int buyItems()
{
   int money_spent = 0;
   for(int i = 0; i < ITEM_COUNT; i++) {
      int price1 = market1Prices[i];
      int price2 = market2Prices[i];
      if(price1 <= market1Point) {
         markets[i] = MARKET1;
         market1Point -= price1;
      } else if(price2 <= market2Point) {
         markets[i] = MARKET2;
         market2Point -= price2;
      } else if(price1 < price2) {
         markets[i] = MARKET1;
         money_spent += price1;
      } else {
         markets[i] = MARKET2;
         money_spent += price2;
      }
   }

   return money_spent;
}

void printMarkets()
{
   std::cout << "MARKETS: {";
   for(int i = 0; i < ITEM_COUNT; i++) {
      if(markets[i] == MARKET1) {
         std::cout << "MARKET1";
      } else {
         std::cout << "MARKET2";
      }
   
      if(i + 1 < ITEM_COUNT) {
         std::cout << ", ";
      }
   }
   std::cout << "}" << std::endl;
}

int main()
{
   int money_spent = buyItems();
   std::cout << "additional money spent: " << money_spent << " TL" << std::endl;
   std::cout << "market1Points: " << market1Point << ", market2Points: " << market2Point << std::endl;
   printMarkets();
}