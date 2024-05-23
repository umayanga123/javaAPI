package model;
public class Student {
    private String id;
    private String name;
    private int programingMark = -1;
    private int databseMark =-1;
    private int totalMark =0;

    public Student(String id,String name){
       this.id = id;
       this.name = name;
    }

    public Student(String id,String name,int programingMark,int databseMark){
        this.id = id;
        this.name = name;
        this.programingMark = programingMark;
        this.databseMark =databseMark;  
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDatabseMark(int databseMark) {
        this.databseMark = databseMark;
    }

    public int getDatabseMark() {
        return databseMark;
    }

    public void setProgramingMark(int programingMark) {
        this.programingMark = programingMark;
    }

    public int getProgramingMark() {
        return programingMark;
    }

    public int getTotalMark(){
        this.totalMark = this.programingMark + this.databseMark;
        return this.totalMark;
    }

    public double getAvgMark(){
        return Math.round(((double) getTotalMark() / 2) * 100.0) / 100.0;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + programingMark;
        result = prime * result + databseMark;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (programingMark != other.programingMark)
            return false;
        if (databseMark != other.databseMark)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", programingMark=" + programingMark + ", databseMark="
                + databseMark + "]";
    }


        

}
