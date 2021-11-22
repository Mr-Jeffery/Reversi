class Player {
    private int pid;
    private static int playerCnt=1;
    private String name;

    public Player(String name){
        this.pid=playerCnt++;
        this.name=name;
        //static++的原理也太神必了罢
        //写成下面这样就摁报错
        // playerCnt++;
        //this.pid=playerCnt;
        //this.name=name;
    }

    public int getPid(){
        return this.pid;
    }

    public String getName(){
        return this.name;
    }

    public static int getPlayerCnt(){
        return playerCnt;
    }

    public void setName(String newName){
        this.name=newName;
    }

    @Override
    public String toString(){
        return "Player: "+this.name+", pid: "+String.valueOf(this.pid);
    }

}
