
#define DEAD 0
#define FULL_HEALTH 100
#define KNIFE_DMG 10
#define GUN_DMG 20
#define GRENADE_DMG 40

enum Weapon { Knife, Gun, Grenade };
enum Hit { Normal, Headshot, Critical };

int roll_the_dice();
enum Hit get_hit_type();
enum Weapon choose_weapon();
int attack(int enemy_health, enum Weapon weapon);
