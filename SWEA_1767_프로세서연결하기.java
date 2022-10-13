import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class SWEA_1767_프로세서연결하기 {
	
	static int N, map[][];
	static int totalCnt, max, res;
	static ArrayList<int[]> list;
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			totalCnt = 0;
			list = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if((i==0 || j==0 || i==N-1 || j==N-1) && map[i][j]==1) continue;
					if(map[i][j]==1) {
						list.add(new int[] {i, j});
						totalCnt++;
					}
				}
			}
			
			max = 0;
			res = Integer.MAX_VALUE;
			
			dfs(0, 0);
			
			sb.append("#" + t + " " + res + '\n');
			
		}
		
		System.out.println(sb);

	}
	
	static void dfs(int idx, int cur) {
		
		if(totalCnt-idx+cur<max) return;
		
		if(idx==totalCnt) {
			int temp = getLength();
			if(max<cur) {
				max = cur;
				res = temp;
			}else if(max==cur) {
				res = Math.min(temp, res);
			}
			return;
		}
		
		int[] temp = list.get(idx);
		int x = temp[0], y = temp[1];
		for(int d=0; d<4; d++) {
			if(isAvailable(x, y, d)) {
				setStatus(x, y, d, 2);
				dfs(idx+1, cur+1);
				setStatus(x, y, d, 0);
			}
		}
		dfs(idx+1, cur);
	}

	private static int getLength() {
		int lCnt = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==2) ++lCnt;
			}
		}
		
		return lCnt;
	}

	private static void setStatus(int x, int y, int d, int status) {
		int nx = x, ny = y;
		
		while(true) {
			nx += dx[d];
			ny += dy[d];
			if(nx<0 || ny<0 || nx>=N || ny>=N) break;
			
			map[nx][ny] = status;
		}
		
	}

	private static boolean isAvailable(int x, int y, int d) {
		int nx = x;
		int ny = y;
		while(true) {
			nx += dx[d];
			ny += dy[d];
			if(nx<0 || ny<0 || nx>=N || ny>=N) break;
			if(map[nx][ny]>=1) return false;
		}
		
		return true;
	}

}
