package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DeadlineTask extends Task{
    LocalDateTime byDate;
    private final static DateTimeFormatter fm = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
    public DeadlineTask(String description, boolean isCompleted, LocalDateTime byDate){
        super(description, isCompleted);
        this.byDate = byDate;
    }

    public DeadlineTask(String description, boolean isCompleted,
                        LocalDateTime createdDate, LocalDateTime byDate){
        super(description, isCompleted, createdDate);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString() + "(by: " + byDate.format(fm) + ")";
    }

    /**
     * Generates Deadline string to be stored in the file.
     */
    @Override
    public String toFileString(){
        return "D" + super.toFileString() + " | " + byDate.format(Parser.dtFormat);
    }
}