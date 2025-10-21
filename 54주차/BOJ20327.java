import java.io.*;
import java.util.*;

public class BOJ20327 {

	public static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		size = (int) Math.pow(2, N);
		int[][] map = new int[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			int subSize = (int) Math.pow(2, l);

			switch (k) {
				case 1:
					map = operation1(map, subSize);
					break;
				case 2:
					map = operation2(map, subSize);
					break;
				case 3:
					map = operation3(map, subSize);
					break;
				case 4:
					map = operation4(map, subSize);
					break;
				case 5:
					map = operation5(map, subSize);
					break;
				case 6:
					map = operation6(map, subSize);
					break;
				case 7:
					map = operation7(map, subSize);
					break;
				case 8:
					map = operation8(map, subSize);
					break;
			}
		}

		printMap(map);
	}

	// 1. 부분 배열 상하 반전
	static int[][] operation1(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		for (int startY = 0; startY < size; startY += subSize) {
			for (int startX = 0; startX < size; startX += subSize) {
				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						temp[startY + subSize - 1 - y][startX + x] = map[startY + y][startX + x];
					}
				}
			}
		}
		return temp;
	}

	// 2. 부분 배열 좌우 반전
	static int[][] operation2(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		for (int startY = 0; startY < size; startY += subSize) {
			for (int startX = 0; startX < size; startX += subSize) {
				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						temp[startY + y][startX + subSize - 1 - x] = map[startY + y][startX + x];
					}
				}
			}
		}
		return temp;
	}

	// 3. 부분 배열 90도 오른쪽 회전
	static int[][] operation3(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		for (int startY = 0; startY < size; startY += subSize) {
			for (int startX = 0; startX < size; startX += subSize) {
				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						temp[startY + x][startX + subSize - 1 - y] = map[startY + y][startX + x];
					}
				}
			}
		}
		return temp;
	}

	// 4. 부분 배열 90도 왼쪽 회전
	static int[][] operation4(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		for (int startY = 0; startY < size; startY += subSize) {
			for (int startX = 0; startX < size; startX += subSize) {
				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						temp[startY + subSize - 1 - x][startX + y] = map[startY + y][startX + x];
					}
				}
			}
		}
		return temp;
	}

	// 5. 덩어리 상하 반전
	static int[][] operation5(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		int gridSize = size / subSize;

		for (int blockY = 0; blockY < gridSize; blockY++) {
			for (int blockX = 0; blockX < gridSize; blockX++) {

				int newBlockY = (gridSize - 1) - blockY;
				int newBlockX = blockX;

				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						int originalY = blockY * subSize + y;
						int originalX = blockX * subSize + x;
						int newY = newBlockY * subSize + y;
						int newX = newBlockX * subSize + x;
						temp[newY][newX] = map[originalY][originalX];
					}
				}
			}
		}
		return temp;
	}

	// 6. 덩어리 좌우 반전
	static int[][] operation6(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		int gridSize = size / subSize;

		for (int blockY = 0; blockY < gridSize; blockY++) {
			for (int blockX = 0; blockX < gridSize; blockX++) {

				int newBlockY = blockY;
				int newBlockX = (gridSize - 1) - blockX;

				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						int originalY = blockY * subSize + y;
						int originalX = blockX * subSize + x;
						int newY = newBlockY * subSize + y;
						int newX = newBlockX * subSize + x;
						temp[newY][newX] = map[originalY][originalX];
					}
				}
			}
		}
		return temp;
	}

	// 7. 덩어리 90도 오른쪽 회전
	static int[][] operation7(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		int gridSize = size / subSize;

		for (int blockY = 0; blockY < gridSize; blockY++) {
			for (int blockX = 0; blockX < gridSize; blockX++) {

				int newBlockY = blockX;
				int newBlockX = (gridSize - 1) - blockY;

				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						int originalY = blockY * subSize + y;
						int originalX = blockX * subSize + x;
						int newY = newBlockY * subSize + y;
						int newX = newBlockX * subSize + x;
						temp[newY][newX] = map[originalY][originalX];
					}
				}
			}
		}
		return temp;
	}

	// 8. 덩어리 90도 왼쪽 회전
	static int[][] operation8(int[][] map, int subSize) {
		int[][] temp = new int[size][size];
		int gridSize = size / subSize;

		for (int blockY = 0; blockY < gridSize; blockY++) {
			for (int blockX = 0; blockX < gridSize; blockX++) {

				int newBlockY = (gridSize - 1) - blockX;
				int newBlockX = blockY;

				for (int y = 0; y < subSize; y++) {
					for (int x = 0; x < subSize; x++) {
						int originalY = blockY * subSize + y;
						int originalX = blockX * subSize + x;
						int newY = newBlockY * subSize + y;
						int newX = newBlockX * subSize + x;
						temp[newY][newX] = map[originalY][originalX];
					}
				}
			}
		}
		return temp;
	}

	static void printMap(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				sb.append(map[y][x]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
}
