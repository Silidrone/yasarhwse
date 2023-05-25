#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "answer.h"

int roll_the_dice(){
    int num;
    num = (rand() % 6) + 1;
    return num;
}

// A function that returns a random number in the range [m, n)
int randomNumberBetween(int m, int n)
{
    return rand() % (n - m);
}

//A function that returns true if a number n is in the range [min, max) and false otherwise
bool isInRange(int n, int min, int max) {
    return n >= min && n <= max - 1;
}

int getRandomByPercentages(int percentages[], int n) {
    int x = randomNumberBetween(0, 100);
    for(int i = 0; i < n; i++) {
        x -= percentages[i];
        if(x <= 0) return i;
    }
}

enum Hit get_hit_type() {
    /*
     * TODO: Randomly choose a hit type
     *   Probability of a NORMAL hit: %75
     *   Probability of a CRITICAL hit: %20
     *   Probability of a HEADSHOT: %5
     *
     * TODO: return the hit type
    */
    int percentages[3] = {75, 5, 20};
    return getRandomByPercentages(percentages, 3);
}

enum Weapon choose_weapon(){
    int weapon;
    printf("Choose a weapon: 1-Knife 2-Gun 3-Grenade\n");
    scanf("%d",&weapon);
    return weapon;
}

enum Action choose_action(){
    int action;
    printf("Choose an action: 1-Attack 2-Heal\n");
    scanf("%d", &action);
    return action;    
}

int attack(int enemy_health, enum Weapon weapon){
    enum Hit hitType;
    int damage;

    if(weapon == Knife){
        damage = KNIFE_DMG;
    }
    else if(weapon == Gun){
        damage = GUN_DMG;
    }
    else if(weapon == Grenade){
        damage = GRENADE_DMG;
    }

    hitType = get_hit_type();
    
    if(hitType == Normal) { //added for the sake of readability, Normal attack does not change the damage
    } else if(hitType == Critical) { 
        damage *= 2;
    } else if(hitType == Headshot) {
        damage = FULL_HEALTH;
    }
    /*
     * TODO: Modify the damage according to the hit type
     *   NORMAL hit does not change the damage
     *   CRITICAl hit doubles the damage
     *   HEADSHOT kills the enemy immediately
     */

    printf("Hits with damage : %d\n", damage);

    enemy_health = enemy_health - damage;
    if(enemy_health < DEAD){
        enemy_health = DEAD;
    }
    return enemy_health;
}

// assuming that a player cannot be healed above it's FULL_HEALTH
int heal(int player_health) {
    if(player_health == FULL_HEALTH) {
        return player_health;
    }
    return player_health + 10;
}

void game(){
    int p1_dice, p2_dice;
    int p1_health, p2_health;
    int weapon;
    int i;

    p1_health = FULL_HEALTH;
    p2_health = FULL_HEALTH;

    printf("The health of p1 : %d\n", p1_health);
    printf("The health of p2 : %d\n", p2_health);

    /*
     * TODO: Continue the game until a player dies
     * TODO: Announce the winner
     */
    
    while(p1_health > DEAD && p2_health > DEAD){
        do {
            p1_dice = roll_the_dice();
            p2_dice = roll_the_dice();
        } while(p1_dice == p2_dice);
        
        printf("The player 1 rolling the dice...:");
        for(i = 0 ; i < 100000000; i++);
        printf("%d\n", p1_dice);

        printf("The player 2 rolling the dice...:");
        for(i = 0 ; i < 100000000; i++);
        printf("%d\n", p2_dice);

        if(p1_dice > p2_dice){
            printf("PLAYER 1's Turn\n");
            enum Action action = choose_action() - 1;
            if (action == Attack){
                weapon = choose_weapon();
                p2_health = attack(p2_health,weapon-1);
            } else if (action == Heal) {
                p1_health = heal(p1_health);
            }
        }
        else if(p2_dice > p1_dice){
            printf("PLAYER 2's Turn\n");
            enum Action action = choose_action() - 1;
            if (action == Attack){
                weapon = choose_weapon();
                p1_health = attack(p1_health,weapon-1);
            } else if (action == Heal) {
                p2_health = heal(p2_health);
            }
        }
        
        printf("Player 1's health : %d\n",p1_health);
        printf("Player 2's health : %d\n",p2_health);
        printf("--------------------------\n");
    }
    
    if(p1_health > DEAD) {
        printf("The winner is Player 1\n");
    } else { //else if (p2_health > DEAD) could have been more suitable to the reader's eye but since there is no other alternative I am leaving it with an else statement
        printf("The winner is Player 2\n");
    }
}

int main(){
    srand(time(NULL));
    game();

    return 0;
}