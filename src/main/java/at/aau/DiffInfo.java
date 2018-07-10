package at.aau;

public class DiffInfo {
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getSrcID() {
        return srcID;
    }

    public void setSrcID(int srcID) {
        this.srcID = srcID;
    }

    public int getSrcStartLine() {
        return srcStartLine;
    }

    public void setSrcStartLine(int srcStartLine) {
        this.srcStartLine = srcStartLine;
    }

    public int getSrcStartLineOffset() {
        return srcStartLineOffset;
    }

    public void setSrcStartLineOffset(int srcStartLineOffset) {
        this.srcStartLineOffset = srcStartLineOffset;
    }

    public int getSrcEndLine() {
        return srcEndLine;
    }

    public void setSrcEndLine(int srcEndLine) {
        this.srcEndLine = srcEndLine;
    }

    public int getSrcEndLineOffset() {
        return srcEndLineOffset;
    }

    public void setSrcEndLineOffset(int srcEndLineOffset) {
        this.srcEndLineOffset = srcEndLineOffset;
    }

    public int getDstID() {
        return dstID;
    }

    public void setDstID(int dstID) {
        this.dstID = dstID;
    }

    public int getDstStartLine() {
        return dstStartLine;
    }

    public void setDstStartLine(int dstStartLine) {
        this.dstStartLine = dstStartLine;
    }

    public int getDstStartLineOffset() {
        return dstStartLineOffset;
    }

    public void setDstStartLineOffset(int dstStartLineOffset) {
        this.dstStartLineOffset = dstStartLineOffset;
    }

    public int getDstEndLine() {
        return dstEndLine;
    }

    public void setDstEndLine(int dstEndLine) {
        this.dstEndLine = dstEndLine;
    }

    public int getDstEndLineOffset() {
        return dstEndLineOffset;
    }

    public void setDstEndLineOffset(int dstEndLineOffset) {
        this.dstEndLineOffset = dstEndLineOffset;
    }

    private String actionType;
    private int srcID;
    private int srcStartLine;
    private int srcStartLineOffset;
    private int srcEndLine;
    private int srcEndLineOffset;
    
    private int dstID;
    private int dstStartLine;
    private int dstStartLineOffset;
    private int dstEndLine;
    private int dstEndLineOffset;
    
}
