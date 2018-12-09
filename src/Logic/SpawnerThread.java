package Logic;

public class SpawnerThread implements Runnable{
	
	private double spawnRate;
	private Hero target;
	private Vector2D spawnPos;
	private boolean isSpawn;
	private double startPos;
	private double endPos;
	private boolean isRight;
	
	public SpawnerThread(double spawnRate,Hero target,double startPos,double endPos) {
		this.spawnRate = spawnRate;
		this.target = target;
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(target.getLife() > 0) {
			try {
				Thread.sleep((long) spawnRate);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double randomPos = (Math.random() * (this.endPos - this.startPos)) + this.startPos;
			int randomSide = (int)(Math.random() * 2 + 1);
			spawnPos = new Vector2D(0,randomPos);
			if(randomSide == 1) {
				spawnPos.setX(-10);
				isRight = true;
			}else {
				spawnPos.setX(1200);
				isRight = false;
			}
			isSpawn = true;
		}
		
	}

	public double getSpawnRate() {
		return spawnRate;
	}

	public void setSpawnRate(double spawnRate) {
		this.spawnRate = spawnRate;
	}

	public Hero getTarget() {
		return target;
	}

	public void setTarget(Hero target) {
		this.target = target;
	}

	public Vector2D getSpawnPos() {
		return spawnPos;
	}

	public void setSpawnPos(Vector2D spawnPos) {
		this.spawnPos = spawnPos;
	}

	public boolean isSpawn() {
		return isSpawn;
	}

	public void setSpawn(boolean isSpawn) {
		this.isSpawn = isSpawn;
	}

	public double getStartPos() {
		return startPos;
	}

	public void setStartPos(double startPos) {
		this.startPos = startPos;
	}

	public double getEndPos() {
		return endPos;
	}

	public void setEndPos(double endPos) {
		this.endPos = endPos;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	
	
}
