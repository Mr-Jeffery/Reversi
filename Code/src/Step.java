class Step {
    private int sid;
    private int rowIndex;
    private int columnIndex;
    private int color;
    private static int stepCnt=1;

    public Step(int rowIndex, int columnIndex, int color){
        this.sid = stepCnt++;
        this.color = color;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getSid(){
        return this.sid;
    }


    public int getColor(){
        return this.color;
    }
    public void setColor(int color){
        this.color = color;
    }


    public int getRowIndex(){
        return this.rowIndex;
    }
    public void setRowIndex(int rowIndex){
        this.rowIndex = rowIndex;
    }


    public int getColumnIndex(){
        return this.columnIndex;
    }
    public void setColumnIndex(int columnIndex){
        this.columnIndex = columnIndex;
    }


    public static int getStepCnt(){
        return stepCnt;
    }// Get the value of StepCnt

    @Override
    public String toString(){
        return "sid: "+this.sid +
                ", rowIndex: "+this.rowIndex +
                ", columnIndex: "+this.columnIndex+
                ", color: "+this.color;
    }
}
