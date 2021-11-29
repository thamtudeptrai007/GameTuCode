package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

	public static final int DEFAULT_SIZE = 16;
	public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
	private static final int TRANSPARENT_COLOR = 0xffff00ff;
	private static final int TRANSPARENT_COLOR_NEW = 0xFFFFFFFF;
	public int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private SpriteSheet _sheet;

	//Dung
	private  int SIZE_X;
	private  int SIZE_Y;

	/*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite grass = new Sprite(16, 16, 0, 0, SpriteSheet.tiles_grass, 16, 16);
	public static Sprite brick = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_brick, 16, 16);
	public static Sprite wall = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_wall, 16, 16);
	public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

	/*
	|--------------------------------------------------------------------------
	| Bomber Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite player_up = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_up1, 12, 16);
	public static Sprite player_down = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_down1, 12, 15);
	public static Sprite player_left = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_left1, 10, 15);
	public static Sprite player_right = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_right1, 10, 16);

	public static Sprite player_up_1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_up2, 12, 16);
	//public static Sprite player_up_2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 15);

	public static Sprite player_down_1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_down2, 12, 15);
	//public static Sprite player_down_2 = new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 12, 16);

	public static Sprite player_left_1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_left2, 11, 16);
	//public static Sprite player_left_2 = new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 12 ,16);

	public static Sprite player_right_1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_right2, 11, 16);
	//public static Sprite player_right_2 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 12, 16);

	public static Sprite player_dead1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_die1, 14, 16);
	public static Sprite player_dead2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_die2, 13, 15);
	public static Sprite player_dead3 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_player_die3, 16, 16);

	/*
	|--------------------------------------------------------------------------
	| Character
	|--------------------------------------------------------------------------
	 */
	//BALLOM  enemy_1_random
	public static Sprite enemy_1_random_left1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_left_1, 16, 16);
	public static Sprite enemy_1_random_left2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_left_2, 16, 16);

	public static Sprite enemy_1_random_right1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_right_1, 16, 16);
	public static Sprite enemy_1_random_right2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_right_2, 16, 16);

	public static Sprite enemy_1_random_up1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_up_1, 16, 16);
	public static Sprite enemy_1_random_up2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_up_2, 16, 16);

	public static Sprite enemy_1_random_down1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_down_1, 16, 16);
	public static Sprite enemy_1_random_down2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_down_2, 16, 16);

	public static Sprite enemy_1_random_dead = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy1_down_1, 16, 16);
	//ONEAL enemy_1_random_speed
	public static Sprite enemy_1_random_speed_left1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_left_1, 16, 16);
	public static Sprite enemy_1_random_speed_left2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_left_2, 16, 16);
	public static Sprite enemy_1_random_speed_right1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_right_1, 16, 16);
	public static Sprite enemy_1_random_speed_right2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_right_2, 16, 16);
	public static Sprite enemy_1_random_speed_up1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_up_1, 16, 16);
	public static Sprite enemy_1_random_speed_up2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_up_2, 16, 16);
	public static Sprite enemy_1_random_speed_down1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_down_1, 16, 16);
	public static Sprite enemy_1_random_speed_down2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_down_2, 16, 16);

	public static Sprite enemy_1_random_speed_dead = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy2_down_1, 16, 16);
	//enemy_3
	public static Sprite enemy_3_left1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_left_1, 16, 16);
	public static Sprite enemy_3_left2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_left_2, 16, 16);
	public static Sprite enemy_3_right1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_right_1, 16, 16);
	public static Sprite enemy_3_right2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_right_2, 16, 16);
	public static Sprite enemy_3_up1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_up_1, 16, 16);
	public static Sprite enemy_3_up2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_up_2, 16, 16);
	public static Sprite enemy_3_down1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_down_1, 16, 16);
	public static Sprite enemy_3_down2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_down_2, 16, 16);

	public static Sprite enemy_3_dead = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_down_1, 16, 16);

	//enemy_4
	public static Sprite enemy_4_left1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_left_1, 16, 16);
	public static Sprite enemy_4_left2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_left_2, 16, 16);
	public static Sprite enemy_4_right1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_right_1, 16, 16);
	public static Sprite enemy_4_right2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_right_2, 16, 16);
	public static Sprite enemy_4_up1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_up_1, 16, 16);
	public static Sprite enemy_4_up2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_up_2, 16, 16);
	public static Sprite enemy_4_down1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_down_1, 16, 16);
	public static Sprite enemy_4_down2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy4_down_2, 16, 16);

	public static Sprite enemy_4_dead = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_enemy3_down_1, 16, 16);

	//Minvo
	public static Sprite minvo_left1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite minvo_left2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite minvo_left3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite minvo_right1 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite minvo_right2 = new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite minvo_right3 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);

	//Kondoria
	public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite kondoria_right1 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoria_right2 = new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoria_right3 = new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

	//ALL
	public static Sprite mob_dead1 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite mob_dead2 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite mob_dead3 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

	/*
	|--------------------------------------------------------------------------
	| Bomb Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_bom1, 15, 15);
	public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_bom2, 13, 15);
	public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_bom3, 12, 14);

	/*
	|--------------------------------------------------------------------------
	| FlameSegment Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite bomb_exploded = new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16);
	public static Sprite bomb_exploded1 = new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite bomb_exploded2 = new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_vertical = new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical1 = new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical2 = new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_horizontal = new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal1 = new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal2 = new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_horizontal_left_last = new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_left_last1 = new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_left_last2 = new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_horizontal_right_last = new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_right_last1 = new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_right_last2 = new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_vertical_top_last = new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_top_last1 = new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_top_last2 = new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_vertical_down_last = new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_down_last1 = new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_down_last2 = new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16);

	/*
	|--------------------------------------------------------------------------
	| Brick FlameSegment
	|--------------------------------------------------------------------------
	 */
	public static Sprite brick_exploded = new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite brick_exploded1 = new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16);
	public static Sprite brick_exploded2 = new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16);

	/*
	|--------------------------------------------------------------------------
	| Powerups
	|--------------------------------------------------------------------------
	 */
	public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_bombs, 16, 16);
	public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_flames, 16, 16);
	public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_speed, 16, 16);
	public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_wallpass, 16, 16);
	public static Sprite powerup_live = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_live, 16, 16);
	public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_bombpass, 16, 16);
	public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles_powerup_flamepass, 14, 14);

	public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}
	//Dung
	public Sprite(int SIZE_X, int SIZE_Y, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = 16;
		_pixels = new int[SIZE_X * SIZE_Y];
		_x = x * SIZE_X;
		_y = y * SIZE_Y;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}
	//

	public Sprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}

	public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;

		if(calc < diff) {
			return normal;
		}

		if(calc < diff * 2) {
			return x1;
		}

		return x2;
	}

	public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2;
	}

	public int getSize() {
		return SIZE;
	}

	public int getPixel(int i) {
		return _pixels[i];
	}

	public Image getFxImage() {
		WritableImage wr = new WritableImage(SIZE, SIZE);
		PixelWriter pw = wr.getPixelWriter();
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				if ( _pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
					pw.setArgb(x, y, 0);
				}
				//else if ( _pixels[x + y * SIZE] == TRANSPARENT_COLOR_NEW) {
				//	pw.setArgb(x, y, 0);
				//}
				else {
					pw.setArgb(x, y, _pixels[x + y * SIZE]);
				}
			}
		}
		Image input = new ImageView(wr).getImage();
		return resample(input, SCALED_SIZE / DEFAULT_SIZE);
	}

	private Image resample(Image input, int scaleFactor) {
		final int W = (int) input.getWidth();
		final int H = (int) input.getHeight();
		final int S = scaleFactor;

		WritableImage output = new WritableImage(
				W * S,
				H * S
		);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
}
