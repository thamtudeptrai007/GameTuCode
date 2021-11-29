package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;

public class Animation {
    private Sprite[] sprites;

    /*
     * Bomber animation.
     */
    public static Animation player_up = new Animation(Sprite.player_up,
            Sprite.player_up_1);
    public static Animation player_down = new Animation(Sprite.player_down,
            Sprite.player_down_1);
    public static Animation player_left = new Animation(Sprite.player_left,
            Sprite.player_left_1);
    public static Animation player_right = new Animation(Sprite.player_right,
            Sprite.player_right_1);
    public static Animation player_dead = new Animation(Sprite.player_dead1,
            Sprite.player_dead2, Sprite.player_dead3, Sprite.grass, Sprite.grass);

    /*
     * Character.
     */
    // enemy_1_random
    public static Animation enemy_1_random_left = new Animation(Sprite.enemy_1_random_left1,
            Sprite.enemy_1_random_left2);
    public static Animation enemy_1_random_right = new Animation(Sprite.enemy_1_random_right1,
            Sprite.enemy_1_random_right2);
    public static Animation enemy_1_random_up = new Animation(Sprite.enemy_1_random_up1,
            Sprite.enemy_1_random_up2);
    public static Animation enemy_1_random_down = new Animation(Sprite.enemy_1_random_down1,
            Sprite.enemy_1_random_down2);
    public static Animation enemy_1_random_dead = new Animation(Sprite.enemy_1_random_dead,
            Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3);

    // enemy_1_random_speed
    public static Animation enemy_1_random_speed_left = new Animation(Sprite.enemy_1_random_speed_left1,
            Sprite.enemy_1_random_speed_left2);
    public static Animation enemy_1_random_speed_right = new Animation(Sprite.enemy_1_random_speed_right1,
            Sprite.enemy_1_random_speed_right2);
    public static Animation enemy_1_random_speed_up = new Animation(Sprite.enemy_1_random_speed_up1,
            Sprite.enemy_1_random_speed_up2);
    public static Animation enemy_1_random_speed_down = new Animation(Sprite.enemy_1_random_speed_down1,
            Sprite.enemy_1_random_speed_down2);
    public static Animation enemy_1_random_speed_dead = new Animation(Sprite.enemy_1_random_speed_dead,
            Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3);

    // enemy_3
    public static Animation enemy_3_left = new Animation(Sprite.enemy_3_left1,
            Sprite.enemy_3_left2);
    public static Animation enemy_3_right = new Animation(Sprite.enemy_3_right1,
            Sprite.enemy_3_right2);
    public static Animation enemy_3_up = new Animation(Sprite.enemy_3_up1,
            Sprite.enemy_3_up2);
    public static Animation enemy_3_down = new Animation(Sprite.enemy_3_down1,
            Sprite.enemy_3_down2);
    public static Animation enemy_3_dead = new Animation(Sprite.enemy_3_dead,
            Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3);

    // Minvo
    public static Animation minvo_left = new Animation(Sprite.minvo_left1,
            Sprite.minvo_left2, Sprite.minvo_left3);
    public static Animation minvo_right = new Animation(Sprite.minvo_right1,
            Sprite.minvo_right2, Sprite.minvo_right3);
    public static Animation minvo_dead = new Animation(Sprite.minvo_dead,
            Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3);

    // Kondoria
    public static Animation kondoria_left = new Animation(Sprite.kondoria_left1,
            Sprite.kondoria_left2, Sprite.kondoria_left3);
    public static Animation kondoria_right = new Animation(Sprite.kondoria_right1,
            Sprite.kondoria_right2, Sprite.kondoria_right3);
    public static Animation kondoria_dead = new Animation(Sprite.kondoria_dead,
            Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3);

    /*
     * Bomb
     */
    public static Animation bomb = new Animation(Sprite.bomb, Sprite.bomb_1,
            Sprite.bomb_2, Sprite.bomb_1);

    /*
     * FlameSegment.
     */

    public static Animation bomb_exploded = new Animation(Sprite.bomb_exploded,
            Sprite.bomb_exploded1, Sprite.bomb_exploded2);
    public static Animation explosion_horizontal = new Animation(Sprite.explosion_horizontal,
            Sprite.explosion_horizontal2,
            Sprite.explosion_horizontal2);
    public static Animation explosion_horizontal_left = new Animation(Sprite.explosion_horizontal_left_last,
            Sprite.explosion_horizontal_left_last1,
            Sprite.explosion_horizontal_left_last2);
    public static Animation explosion_horizontal_right = new Animation(Sprite.explosion_horizontal_right_last,
            Sprite.explosion_horizontal_right_last1,
            Sprite.explosion_horizontal_right_last2);
    public static Animation explosion_vertical = new Animation(Sprite.explosion_vertical,
            Sprite.explosion_vertical1,
            Sprite.explosion_vertical2);
    public static Animation explosion_vertical_top = new Animation(Sprite.explosion_vertical_top_last,
            Sprite.explosion_vertical_top_last1,
            Sprite.explosion_vertical_top_last2);
    public static Animation explosion_vertical_down = new Animation(Sprite.explosion_vertical_down_last,
            Sprite.explosion_vertical_down_last1,
            Sprite.explosion_vertical_down_last2);

    /*
     * Brick FlameSegment
     */

    public static Animation brick = new Animation(Sprite.brick);
    public static Animation brick_explode = new Animation(Sprite.brick_exploded, Sprite.brick_exploded1,
            Sprite.brick_exploded2);

    /*
     * Powerups
     */

    public static Animation powerup_bombpass = new Animation(Sprite.powerup_bombpass);
    public static Animation powerup_bombs = new Animation(Sprite.powerup_bombs);
    public static Animation powerup_detonator = new Animation(Sprite.powerup_live);
    public static Animation powerup_flamepass = new Animation(Sprite.powerup_flamepass);
    public static Animation powerup_flames = new Animation(Sprite.powerup_flames);
    public static Animation powerup_speed = new Animation(Sprite.powerup_speed);
    public static Animation powerup_wallpass = new Animation(Sprite.powerup_wallpass);

    /*
    Tạo ds Sprite
     */
    public Animation(Sprite... images) {
        sprites = new Sprite[images.length];
        System.arraycopy(images, 0, sprites, 0, images.length);
    }

    public Image[] getFxImages() {
        Image[] images = new Image[sprites.length];
        for (int i = 0; i < sprites.length; i++) {
            images[i] = sprites[i].getFxImage();
        }
        return images;
    }
}
