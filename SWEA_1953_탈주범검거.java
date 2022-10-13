import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_1953_탈주범검거 { 

	static int[][] tunnel = { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, // 상우하좌
			{ 1, 0, 1, 0 }, // 상하
			{ 0, 1, 0, 1 }, // 우좌
			{ 1, 1, 0, 0 }, // 상우
			{ 0, 1, 1, 0 }, // 우하
			{ 0, 0, 1, 1 }, // 하좌
			{ 1, 0, 0, 1 }, // 상좌
	};

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int N, M, L, res;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 세로
			M = Integer.parseInt(st.nextToken()); // 지도 가로
			int R = Integer.parseInt(st.nextToken()); // 맨홀 세로 위치
			int C = Integer.parseInt(st.nextToken()); // 맨홀 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			res = 1;
			bfs(R, C);

			sb.append("#" + t + " " + res + '\n');
		}

		System.out.println(sb);

	}

	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];

		q.offer(new int[] { r, c });
		visited[r][c] = true;

		int time = 1;
		while (!q.isEmpty()) {
			int size = q.size();

			if (time == L) {
				return;
			}

			for (int i = 0; i < size; i++) {
				int[] now = q.poll();
				int x = now[0], y = now[1];
				int[] t = tunnel[map[x][y]];

				for (int d = 0; d < 4; d++) {
					if (t[d] == 0)
						continue;

					int nx = x + dx[d];
					int ny = y + dy[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (map[nx][ny] == 0 || visited[nx][ny])
						continue;

					if (tunnel[map[nx][ny]][(d + 2) % 4] == 1) {
						q.offer(new int[] { nx, ny });
						visited[nx][ny] = true;
						res++;
					}
				}
			}
			time++;
		}

	}

}
