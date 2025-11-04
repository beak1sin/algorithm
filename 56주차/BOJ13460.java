import java.io.*;
import java.util.*;

class State {
	int ry, rx;
	int by, bx;
	int count;

	State(int ry, int rx, int by, int bx, int count) {
		this.ry = ry;
		this.rx = rx;
		this.by = by;
		this.bx = bx;
		this.count = count;
	}
}

public class BOJ13460 {

	public static int N, M;
	public static char[][] map;
	public static boolean[][][][] visited;

	public static int[] dy = {-1, 1, 0, 0};
	public static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		int startRy = 0, startRx = 0, startBy = 0, startBx = 0;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'R') {
					startRy = i;
					startRx = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					startBy = i;
					startBx = j;
					map[i][j] = '.';
				}
			}
		}

		System.out.println(bfs(startRy, startRx, startBy, startBx));
	}

	public static int bfs(int startRy, int startRx, int startBy, int startBx) {
		Queue<State> queue = new ArrayDeque<>();
		queue.add(new State(startRy, startRx, startBy, startBx, 1));
		visited[startRy][startRx][startBy][startBx] = true;

		while (!queue.isEmpty()) {
			State curr = queue.poll();

			// 10번을 초과하면 탐색 중지
			if (curr.count >= 11) {
				continue;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nry = curr.ry;
				int nrx = curr.rx;
				int nby = curr.by;
				int nbx = curr.bx;

				int redMoves = 0;
				int blueMoves = 0;

				boolean redInHole = false;
				boolean blueInHole = false;

				// 빨간 구슬 이동
				while (map[nry + dy[dir]][nrx + dx[dir]] != '#') {
					nry += dy[dir];
					nrx += dx[dir];
					redMoves++;
					if (map[nry][nrx] == 'O') {
						redInHole = true;
						break;
					}
				}

				// 파란 구슬 이동
				while (map[nby + dy[dir]][nbx + dx[dir]] != '#') {
					nby += dy[dir];
					nbx += dx[dir];
					blueMoves++;
					if (map[nby][nbx] == 'O') {
						blueInHole = true;
						break;
					}
				}

				// 파란 구슬이 구멍에 빠진 경우
				if (blueInHole) {
					continue;
				}

				// 파란 구슬은 안 빠졌는데, 빨간 구슬만 빠진 경우
				if (redInHole) {
					return curr.count;
				}

				// 둘 다 구멍에 안 빠졌는데, 위치가 겹친 경우
				if (nry == nby && nrx == nbx) {
					if (redMoves > blueMoves) {
						nry -= dy[dir];
						nrx -= dx[dir];
					} else {
						nby -= dy[dir];
						nbx -= dx[dir];
					}
				}

				if (!visited[nry][nrx][nby][nbx]) {
					visited[nry][nrx][nby][nbx] = true;
					queue.add(new State(nry, nrx, nby, nbx, curr.count + 1));
				}
			}
		}

		return -1;
	}
}

