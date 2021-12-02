package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {

	private String _path;
	public final int SIZE;
	public int[] _pixels;
	public BufferedImage image;

	//load anh map
	public static SpriteSheet tiles_grass = new SpriteSheet("/new_sprites_32/grass_blue_1.png", 32);
	public static SpriteSheet tiles_wall = new SpriteSheet("/new_sprites_32/wall.png", 32);
	public static SpriteSheet tiles_brick = new SpriteSheet("/new_sprites_32/brick.png", 32);

	//load anh player
	public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);
	public static SpriteSheet tiles_player_down1 = new SpriteSheet("/new_sprites_32/player_down_1.png", 32);
	public static SpriteSheet tiles_player_down2 = new SpriteSheet("/new_sprites_32/player_down_2.png", 32);
	public static SpriteSheet tiles_player_up1 = new SpriteSheet("/new_sprites_32/player_up_1.png", 32);
	public static SpriteSheet tiles_player_up2 = new SpriteSheet("/new_sprites_32/player_up_2.png", 32);
	public static SpriteSheet tiles_player_left1 = new SpriteSheet("/new_sprites_32/player_left_1.png", 32);
	public static SpriteSheet tiles_player_left2 = new SpriteSheet("/new_sprites_32/player_left_2.png", 32);
	public static SpriteSheet tiles_player_right1 = new SpriteSheet("/new_sprites_32/player_right_1.png", 32);
	public static SpriteSheet tiles_player_right2 = new SpriteSheet("/new_sprites_32/player_right_2.png", 32);
	public static SpriteSheet tiles_player_die1 = new SpriteSheet("/new_sprites_32/player_down_dead_1.png", 32);
	public static SpriteSheet tiles_player_die2 = new SpriteSheet("/new_sprites_32/player_down_dead_2 .png", 32);
	public static SpriteSheet tiles_player_die3 = new SpriteSheet("/new_sprites_32/player_down_dead_3.png", 32);

	//load anh items
	public static SpriteSheet tiles_bom1 = new SpriteSheet("/new_sprites_32/bomb.png", 32);
	public static SpriteSheet tiles_bom2 = new SpriteSheet("/new_sprites_32/bomb_1.png", 32);
	public static SpriteSheet tiles_bom3 = new SpriteSheet("/new_sprites_32/bomb_2.png", 32);

	//load anh item
	public static SpriteSheet tiles_powerup_bombs = new SpriteSheet("/new_sprites_32/powerup_bombs.png", 32);
	public static SpriteSheet tiles_powerup_bombpass = new SpriteSheet("/new_sprites_32/powerup_bombpass.png", 32);
	public static SpriteSheet tiles_powerup_live = new SpriteSheet("/new_sprites_32/powerup_detonator.png", 32);
	public static SpriteSheet tiles_powerup_flamepass = new SpriteSheet("/new_sprites_32/powerup_flamepass.png", 32);
	public static SpriteSheet tiles_powerup_flames = new SpriteSheet("/new_sprites_32/powerup_flames.png", 32);
	public static SpriteSheet tiles_powerup_speed = new SpriteSheet("/new_sprites_32/powerup_speed.png", 32);
	public static SpriteSheet tiles_powerup_wallpass = new SpriteSheet("/new_sprites_32/powerup_wallpass.png", 32);

	//load anh enemy
	public static SpriteSheet tiles_enemy1_down_1 = new SpriteSheet("/new_sprites_32/enemy1_down_1.png", 32);
	public static SpriteSheet tiles_enemy1_down_2 = new SpriteSheet("/new_sprites_32/enemy1_down_2.png", 32);
	public static SpriteSheet tiles_enemy1_up_1 = new SpriteSheet("/new_sprites_32/enemy1_up_1.png", 32);
	public static SpriteSheet tiles_enemy1_up_2 = new SpriteSheet("/new_sprites_32/enemy1_up_2.png", 32);
	public static SpriteSheet tiles_enemy1_left_1 = new SpriteSheet("/new_sprites_32/enemy1_left_1.png", 32);
	public static SpriteSheet tiles_enemy1_left_2 = new SpriteSheet("/new_sprites_32/enemy1_left_2.png", 32);
	public static SpriteSheet tiles_enemy1_right_1 = new SpriteSheet("/new_sprites_32/enemy1_right_1.png", 32);
	public static SpriteSheet tiles_enemy1_right_2 = new SpriteSheet("/new_sprites_32/enemy1_right_2.png", 32);
	public static SpriteSheet tiles_enemy1_dead_1 = new SpriteSheet("/new_sprites_32/enemy1_dead_1.png", 32);
	public static SpriteSheet tiles_enemy1_dead_2 = new SpriteSheet("/new_sprites_32/enemy1_dead_2.png", 32);
	public static SpriteSheet tiles_enemy1_dead_3 = new SpriteSheet("/new_sprites_32/enemy1_dead_3.png", 32);
	public static SpriteSheet tiles_enemy1_dead_4 = new SpriteSheet("/new_sprites_32/enemy1_dead_4.png", 32);


	public static SpriteSheet tiles_enemy2_down_1 = new SpriteSheet("/new_sprites_32/enemy1_down_1_gr.png", 32);
	public static SpriteSheet tiles_enemy2_down_2 = new SpriteSheet("/new_sprites_32/enemy1_down_2_gr.png", 32);
	public static SpriteSheet tiles_enemy2_up_1 = new SpriteSheet("/new_sprites_32/enemy1_up_1_gr.png", 32);
	public static SpriteSheet tiles_enemy2_up_2 = new SpriteSheet("/new_sprites_32/enemy1_up_2_gr.png", 32);
	public static SpriteSheet tiles_enemy2_left_1 = new SpriteSheet("/new_sprites_32/enemy1_left_1_gr.png", 32);
	public static SpriteSheet tiles_enemy2_left_2 = new SpriteSheet("/new_sprites_32/enemy1_left_2_gr.png", 32);
	public static SpriteSheet tiles_enemy2_right_1 = new SpriteSheet("/new_sprites_32/enemy1_right_1_gr.png", 32);
	public static SpriteSheet tiles_enemy2_right_2 = new SpriteSheet("/new_sprites_32/enemy1_right_2_gr.png", 32);
	public static SpriteSheet tiles_enemy2_dead_1 = new SpriteSheet("/new_sprites_32/enemy1_dead_1_gr.png", 32);
	public static SpriteSheet tiles_enemy2_dead_2 = new SpriteSheet("/new_sprites_32/enemy1_dead_2_gr.png", 32);
	public static SpriteSheet tiles_enemy2_dead_3 = new SpriteSheet("/new_sprites_32/enemy1_dead_3_gr.png", 32);
	public static SpriteSheet tiles_enemy2_dead_4 = new SpriteSheet("/new_sprites_32/enemy1_dead_4_gr.png", 32);

	public static SpriteSheet tiles_enemy3_down_1 = new SpriteSheet("/new_sprites_32/enemy3_down_1.png", 32);
	public static SpriteSheet tiles_enemy3_down_2 = new SpriteSheet("/new_sprites_32/enemy3_down_2.png", 32);
	public static SpriteSheet tiles_enemy3_up_1 = new SpriteSheet("/new_sprites_32/enemy3_up_1.png", 32);
	public static SpriteSheet tiles_enemy3_up_2 = new SpriteSheet("/new_sprites_32/enemy3_up_2.png", 32);
	public static SpriteSheet tiles_enemy3_left_1 = new SpriteSheet("/new_sprites_32/enemy3_left_1.png", 32);
	public static SpriteSheet tiles_enemy3_left_2 = new SpriteSheet("/new_sprites_32/enemy3_left_2.png", 32);
	public static SpriteSheet tiles_enemy3_right_1 = new SpriteSheet("/new_sprites_32/enemy3_right_1.png", 32);
	public static SpriteSheet tiles_enemy3_right_2 = new SpriteSheet("/new_sprites_32/enemy3_right_2.png", 32);
	public static SpriteSheet tiles_enemy3_dead_1 = new SpriteSheet("/new_sprites_32/enemy3_dead_1.png", 32);
	public static SpriteSheet tiles_enemy3_dead_2 = new SpriteSheet("/new_sprites_32/enemy3_dead_2.png", 32);
	public static SpriteSheet tiles_enemy3_dead_3 = new SpriteSheet("/new_sprites_32/enemy3_dead_3.png", 32);
	public static SpriteSheet tiles_enemy3_dead_4 = new SpriteSheet("/new_sprites_32/enemy3_dead_4.png", 32);

	public static SpriteSheet tiles_enemy4_down_1 = new SpriteSheet("/new_sprites_32/enemy4_down_1.png", 32);
	public static SpriteSheet tiles_enemy4_down_2 = new SpriteSheet("/new_sprites_32/enemy4_down_2.png", 32);
	public static SpriteSheet tiles_enemy4_up_1 = new SpriteSheet("/new_sprites_32/enemy4_up_1.png", 32);
	public static SpriteSheet tiles_enemy4_up_2 = new SpriteSheet("/new_sprites_32/enemy4_up_2.png", 32);
	public static SpriteSheet tiles_enemy4_left_1 = new SpriteSheet("/new_sprites_32/enemy4_left_1.png", 32);
	public static SpriteSheet tiles_enemy4_left_2 = new SpriteSheet("/new_sprites_32/enemy4_left_2.png", 32);
	public static SpriteSheet tiles_enemy4_right_1 = new SpriteSheet("/new_sprites_32/enemy4_right_1.png", 32);
	public static SpriteSheet tiles_enemy4_right_2 = new SpriteSheet("/new_sprites_32/enemy4_right_2.png", 32);

	public static SpriteSheet tiles_enemy5_left_1 = new SpriteSheet("/new_sprites_32/kondoria_left2.png", 32);
	public static SpriteSheet tiles_enemy5_left_2 = new SpriteSheet("/new_sprites_32/kondoria_left3.png", 32);
	public static SpriteSheet tiles_enemy5_right_1 = new SpriteSheet("/new_sprites_32/kondoria_right3.png", 32);
	public static SpriteSheet tiles_enemy5_right_2 = new SpriteSheet("/new_sprites_32/kondoria_right2.png", 32);
	public static SpriteSheet tiles_enemy5_dead = new SpriteSheet("/new_sprites_32/kondoria_dead.png", 32);



	//FlameSegment Sprites
	public static SpriteSheet tiles_bomb_exploded = new SpriteSheet("/new_sprites_32/bomb_exploded.png", 32);
	public static SpriteSheet tiles_bomb_exploded1 = new SpriteSheet("/new_sprites_32/bomb_exploded1.png", 32);
	public static SpriteSheet tiles_bomb_exploded2 = new SpriteSheet("/new_sprites_32/bomb_exploded2.png", 32);

	public static SpriteSheet tiles_explosion_vertical = new SpriteSheet("/new_sprites_32/explosion_vertical.png", 32);
	public static SpriteSheet tiles_explosion_vertical1 = new SpriteSheet("/new_sprites_32/explosion_vertical1.png", 32);
	public static SpriteSheet tiles_explosion_vertical2 = new SpriteSheet("/new_sprites_32/explosion_vertical2.png", 32);

	public static SpriteSheet tiles_explosion_horizontal = new SpriteSheet("/new_sprites_32/explosion_horizontal.png", 32);
	public static SpriteSheet tiles_explosion_horizontal1 = new SpriteSheet("/new_sprites_32/explosion_horizontal1.png", 32);
	public static SpriteSheet tiles_explosion_horizontal2 = new SpriteSheet("/new_sprites_32/explosion_horizontal2.png", 32);

	public static SpriteSheet tiles_horizontal_left_last = new SpriteSheet("/new_sprites_32/explosion_horizontal_left_last.png", 32);
	public static SpriteSheet tiles_horizontal_left_last1 = new SpriteSheet("/new_sprites_32/explosion_horizontal_left_last1.png", 32);
	public static SpriteSheet tiles_horizontal_left_last2 = new SpriteSheet("/new_sprites_32/explosion_horizontal_left_last.png", 32);

	public static SpriteSheet tiles_horizontal_right_last = new SpriteSheet("/new_sprites_32/explosion_horizontal_right_last.png", 32);
	public static SpriteSheet tiles_horizontal_right_last1 = new SpriteSheet("/new_sprites_32/explosion_horizontal_right_last1.png", 32);
	public static SpriteSheet tiles_horizontal_right_last2 = new SpriteSheet("/new_sprites_32/explosion_horizontal_right_last2.png", 32);

	public static SpriteSheet tiles_vertical_top_last = new SpriteSheet("/new_sprites_32/explosion_vertical_top_last.png", 32);
	public static SpriteSheet tiles_vertical_top_last1 = new SpriteSheet("/new_sprites_32/explosion_vertical_top_last1.png", 32);
	public static SpriteSheet tiles_vertical_top_last2 = new SpriteSheet("/new_sprites_32/explosion_vertical_top_last2.png", 32);

	public static SpriteSheet tiles_vertical_down_last = new SpriteSheet("/new_sprites_32/explosion_vertical_down_last.png", 32);
	public static SpriteSheet tiles_vertical_down_last1 = new SpriteSheet("/new_sprites_32/explosion_vertical_down_last1.png", 32);
	public static SpriteSheet tiles_vertical_down_last2 = new SpriteSheet("/new_sprites_32/explosion_vertical_down_last2.png", 32);

	// load anh brick exploded
	public static SpriteSheet tiles_brick_exploded = new SpriteSheet("/new_sprites_32/brick_exploded.png", 32);
	public static SpriteSheet tiles_brick_exploded1 = new SpriteSheet("/new_sprites_32/brick_exploded1.png", 32);
	public static SpriteSheet tiles_brick_exploded2 = new SpriteSheet("/new_sprites_32/brick_exploded2.png", 32);


	public SpriteSheet(String path, int size) {
		_path = path;
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			URL a = SpriteSheet.class.getResource(_path);
			image = ImageIO.read(a);
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, _pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
