import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
 
public class SWEA_1249_보급로 {
	
	static int N, map[][], res;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String input = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = input.charAt(j)-'0';
				}
			}
			
			res = Integer.MAX_VALUE;
			bfs();
			
			sb.append("#" + t + " " + res + '\n');
		}
		
		System.out.println(sb);

	}
	
	static void bfs() {
		Queue<Data> q = new PriorityQueue<>();
		q.offer(new Data(0, 0, 0));
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Data now = q.poll();
			int x = now.x, y = now.y;
			
			if(x==N-1 && y==N-1) {
				res = Math.min(res, now.cnt);
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				if(visited[nx][ny]) continue;
				
				q.offer(new Data(nx, ny, now.cnt + map[nx][ny]));
				visited[nx][ny] = true;
			}
		}
	}
	
	static class Data implements Comparable<Data>{
		int x, y;
		int cnt;
		
		public Data(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Data o) {
			return this.cnt - o.cnt;
		}
	}

}
