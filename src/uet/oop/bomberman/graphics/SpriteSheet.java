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

	//load anh player
	public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);
	public static SpriteSheet tiles_player_down1 = new SpriteSheet("/new_sprites/player_down_1_gr.png", 16);
	public static SpriteSheet tiles_player_down2 = new SpriteSheet("/new_sprites/player_down_2_gr.png", 16);
	public static SpriteSheet tiles_player_up1 = new SpriteSheet("/new_sprites/player_up_1_gr.png", 16);
	public static SpriteSheet tiles_player_up2 = new SpriteSheet("/new_sprites/player_up_2_gr.png", 16);
	public static SpriteSheet tiles_player_left1 = new SpriteSheet("/new_sprites/player_left_1_gr.png", 16);
	public static SpriteSheet tiles_player_left2 = new SpriteSheet("/new_sprites/player_left_2_gr.png", 16);
	public static SpriteSheet tiles_player_right1 = new SpriteSheet("/new_sprites/player_right_1_gr.png", 16);
	public static SpriteSheet tiles_player_right2 = new SpriteSheet("/new_sprites/player_right_2_gr.png", 16);
	public static SpriteSheet tiles_player_die1 = new SpriteSheet("/new_sprites/player_die_1_gr.png", 16);
	public static SpriteSheet tiles_player_die2 = new SpriteSheet("/new_sprites/player_die_2_gr.png", 16);
	public static SpriteSheet tiles_player_die3 = new SpriteSheet("/new_sprites/player_die_3_gr.png", 16);

	//load anh...
	public static SpriteSheet tiles_grass = new SpriteSheet("/new_sprites/grass_brown.png", 16);
	public static SpriteSheet tiles_wall = new SpriteSheet("/new_sprites/wall1.png", 16);
	public static SpriteSheet tiles_brick = new SpriteSheet("/new_sprites/brick.png", 16);

	//load anh items
	public static SpriteSheet tiles_bom1 = new SpriteSheet("/new_sprites/bom_1_pts.png", 16);
	public static SpriteSheet tiles_bom2 = new SpriteSheet("/new_sprites/bom_2_pts.png", 16);
	public static SpriteSheet tiles_bom3 = new SpriteSheet("/new_sprites/bom_3_pts.png", 16);

	//load anh item
	public static SpriteSheet tiles_powerup_bombs = new SpriteSheet("/new_sprites/powerup_bombs.png", 16);
	public static SpriteSheet tiles_powerup_bombpass = new SpriteSheet("/new_sprites/powerup_bombpass.png", 16);
	public static SpriteSheet tiles_powerup_live = new SpriteSheet("/new_sprites/powerup_live.png", 16);
	public static SpriteSheet tiles_powerup_flamepass = new SpriteSheet("/new_sprites/powerup_flamepass.png", 16);
	public static SpriteSheet tiles_powerup_flames = new SpriteSheet("/new_sprites/powerup_flames.png", 16);
	public static SpriteSheet tiles_powerup_speed = new SpriteSheet("/new_sprites/powerup_speed.png", 16);
	public static SpriteSheet tiles_powerup_wallpass = new SpriteSheet("/new_sprites/powerup_wallpass.png", 16);

	//load anh boss (enemy)
	public static SpriteSheet tiles_enemy1_down_1 = new SpriteSheet("/new_sprites/enemy1_down_1.png", 16);
	public static SpriteSheet tiles_enemy1_down_2 = new SpriteSheet("/new_sprites/enemy1_down_2.png", 16);
	public static SpriteSheet tiles_enemy1_up_1 = new SpriteSheet("/new_sprites/enemy1_up_1.png", 16);
	public static SpriteSheet tiles_enemy1_up_2 = new SpriteSheet("/new_sprites/enemy1_up_2.png", 16);
	public static SpriteSheet tiles_enemy1_left_1 = new SpriteSheet("/new_sprites/enemy1_left_1.png", 16);
	public static SpriteSheet tiles_enemy1_left_2 = new SpriteSheet("/new_sprites/enemy1_left_2.png", 16);
	public static SpriteSheet tiles_enemy1_right_1 = new SpriteSheet("/new_sprites/enemy1_right_1.png", 16);
	public static SpriteSheet tiles_enemy1_right_2 = new SpriteSheet("/new_sprites/enemy1_right_2.png", 16);


	public static SpriteSheet tiles_enemy2_down_1 = new SpriteSheet("/new_sprites/enemy2_down_1.png", 16);
	public static SpriteSheet tiles_enemy2_down_2 = new SpriteSheet("/new_sprites/enemy2_down_2.png", 16);
	public static SpriteSheet tiles_enemy2_up_1 = new SpriteSheet("/new_sprites/enemy2_up_1.png", 16);
	public static SpriteSheet tiles_enemy2_up_2 = new SpriteSheet("/new_sprites/enemy2_up_2.png", 16);
	public static SpriteSheet tiles_enemy2_left_1 = new SpriteSheet("/new_sprites/enemy2_left_1.png", 16);
	public static SpriteSheet tiles_enemy2_left_2 = new SpriteSheet("/new_sprites/enemy2_left_2.png", 16);
	public static SpriteSheet tiles_enemy2_right_1 = new SpriteSheet("/new_sprites/enemy2_right_1.png", 16);
	public static SpriteSheet tiles_enemy2_right_2 = new SpriteSheet("/new_sprites/enemy2_right_2.png", 16);

	public static SpriteSheet tiles_enemy3_down_1 = new SpriteSheet("/new_sprites/enemy3_down_1.png", 16);
	public static SpriteSheet tiles_enemy3_down_2 = new SpriteSheet("/new_sprites/enemy3_down_2.png", 16);
	public static SpriteSheet tiles_enemy3_up_1 = new SpriteSheet("/new_sprites/enemy3_up_1.png", 16);
	public static SpriteSheet tiles_enemy3_up_2 = new SpriteSheet("/new_sprites/enemy3_up_2.png", 16);
	public static SpriteSheet tiles_enemy3_left_1 = new SpriteSheet("/new_sprites/enemy3_left_1.png", 16);
	public static SpriteSheet tiles_enemy3_left_2 = new SpriteSheet("/new_sprites/enemy3_left_2.png", 16);
	public static SpriteSheet tiles_enemy3_right_1 = new SpriteSheet("/new_sprites/enemy3_right_1.png", 16);
	public static SpriteSheet tiles_enemy3_right_2 = new SpriteSheet("/new_sprites/enemy3_right_2.png", 16);

	public static SpriteSheet tiles_enemy4_down_1 = new SpriteSheet("/new_sprites/enemy4_down_1.png", 16);
	public static SpriteSheet tiles_enemy4_down_2 = new SpriteSheet("/new_sprites/enemy4_down_2.png", 16);
	public static SpriteSheet tiles_enemy4_up_1 = new SpriteSheet("/new_sprites/enemy4_up_1.png", 16);
	public static SpriteSheet tiles_enemy4_up_2 = new SpriteSheet("/new_sprites/enemy4_up_2.png", 16);
	public static SpriteSheet tiles_enemy4_left_1 = new SpriteSheet("/new_sprites/enemy4_left_1.png", 16);
	public static SpriteSheet tiles_enemy4_left_2 = new SpriteSheet("/new_sprites/enemy4_left_2.png", 16);
	public static SpriteSheet tiles_enemy4_right_1 = new SpriteSheet("/new_sprites/enemy4_right_1.png", 16);
	public static SpriteSheet tiles_enemy4_right_2 = new SpriteSheet("/new_sprites/enemy4_right_2.png", 16);


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
