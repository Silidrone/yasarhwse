#include <stdbool.h>

#define DEAD 0
#define FULL_HEALTH 100
#define KNIFE_DMG 10
#define GUN_DMG 20
#define GRENADE_DMG 40

enum Weapon { Knife, Gun, Grenade };
enum Hit { Normal, Headshot, Critical };
enum Action { Attack, Heal };

int roll_the_dice();
int randomNumberBetween(int m, int n);
bool isInRange(int n, int min, int max);
int getRandomByPercentages(int percentages[], int n);
enum Hit get_hit_type();
enum Weapon choose_weapon();
enum Action choose_action();
int attack(int enemy_health, enum Weapon weapon);
