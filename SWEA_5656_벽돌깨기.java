import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {

	static int N, W, H, res;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T  = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = Integer.MAX_VALUE;
			go(map, 0);
			
			System.out.println("#" + t + " " + res);
		}

	}
	
	static boolean go(int[][] map, int idx) {
		
		// 남아 있는 벽돌 개수 구하기
		int cnt = getRemain(map);
		if(cnt == 0) {
			res = 0;
			return true; 
		}
		
		if(idx == N) {
			res = Math.min(cnt, res);
			return false;
		}
		
		int[][] newMap = new int[H][W];
		
		for(int x=0; x<W; x++) {
			int y = 0;
			while(y<H && map[y][x] == 0) y++;
			
			if(y==H) continue;
			else {
				copy(map, newMap);
				boom(newMap, y, x, map[y][x]);
				down(newMap);
				if(go(newMap, idx+1)) return true;
			}
		}
		
		return false;
	}

	private static void down(int[][] map) {
		Stack<Integer> s = new Stack<>();
		for(int x=0; x<W; x++) {
			int y = 0;
			for(; y<H; y++) {
				if(map[y][x]>0) {
					s.push(map[y][x]);
					map[y][x] = 0;
				}
			}
			y = H-1;
			while(!s.isEmpty()) {
				map[y][x] = s.pop();
				y--;
			}
		}
		
	}

	private static void boom(int[][] map, int y, int x, int idx) {
		
		map[y][x] = 0;
		if(idx==1) return;
		
		for(int d=0; d<4; d++) {
			int ny = y;
			int nx = x;
			for(int k=1; k<idx; k++) {
				ny += dy[d];
				nx += dx[d];
				
				if(nx<0 || ny<0 || nx>=W || ny>=H) continue;
				if(map[ny][nx]==0) continue;
				
				boom(map, ny, nx, map[ny][nx]);
			}
		}
	}

	private static void copy(int[][] map, int[][] newMap) {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
	}

	private static int getRemain(int[][] map) {
		int cnt = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[i][j]>0) cnt++;
			}
		}
		
		return cnt;
	}

}
