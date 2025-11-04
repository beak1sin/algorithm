import java.io.*;
import java.util.*;

class Shark {
	int s; // 속력
	int d; // 방향
	int z; // 크기

	Shark(int s, int d, int z) {
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class BOJ17143 {

	public static int R, C, M;
	public static Shark[][] map;

	public static int[] dy = {0, -1, 1, 0, 0};
	public static int[] dx = {0, 0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R + 1][C + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[y][x] = new Shark(s, d, z);
		}

		int totalSharkSize = 0;

		for (int j = 1; j <= C; j++) {
			totalSharkSize += catchShark(j);
			moveSharks();
		}

		System.out.println(totalSharkSize);
	}

	public static int catchShark(int x) {
		for (int y = 1; y <= R; y++) {
			if (map[y][x] != null) {
				int size = map[y][x].z;
				map[y][x] = null;
				return size;
			}
		}
		return 0;
	}

	public static void moveSharks() {
		Shark[][] nextMap = new Shark[R + 1][C + 1];

		for (int y = 1; y <= R; y++) {
			for (int x = 1; x <= C; x++) {
				if (map[y][x] != null) {
					Shark shark = map[y][x];

					int[] newPos = calculateNewPos(y, x, shark.s, shark.d);
					int ny = newPos[0];
					int nx = newPos[1];
					int nd = newPos[2];

					shark.d = nd;

					if (nextMap[ny][nx] == null) {
						nextMap[ny][nx] = shark;
					} else {
						if (shark.z > nextMap[ny][nx].z) {
							nextMap[ny][nx] = shark;
						}
					}
				}
			}
		}

		map = nextMap;
	}

	public static int[] calculateNewPos(int y, int x, int s, int d) {
		int ny = y;
		int nx = x;
		int nd = d;

		if (nd == 1 || nd == 2) {
			for (int i = 0; i < s; i++) {
				if (ny == 1 && nd == 1) nd = 2;
				else if (ny == R && nd == 2) nd = 1;

				ny += dy[nd];
			}
		}
		else {
			for (int i = 0; i < s; i++) {
				if (nx == 1 && nd == 4) nd = 3;
				else if (nx == C && nd == 3) nd = 4;

				nx += dx[nd];
			}
		}

		return new int[]{ny, nx, nd};
	}
}

