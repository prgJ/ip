package Duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }

    public int getNumTasks(){
        return taskList.size();
    }

    /**
     * Process the printing of a task.
     */
    public void processPrintList(Ui uiPrinter){
        uiPrinter.printMessage(Ui.listMsg);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i + 1) +". " + currentTask);
        }
    }

    /**
     * Process the marking status of a task.
     */
    public boolean processMarkingTask(String taskIndexStr, boolean isCompleted, Ui uiPrinter) throws DukeException{
        int markingIndex = getExistingIndex(taskIndexStr);
        if(markingIndex != -1) {
            taskList.get(markingIndex).setCompleted(isCompleted);
            uiPrinter.printMessage(Ui.getCompleteMessage(isCompleted));
            processPrintList(uiPrinter);
            return true;
        }
        else{
            uiPrinter.printMessage("Fail to set task complete status!");
            return false;
        }
    }

    public void checkCommandData(String[] taskInfo, int expectedInfoNum) throws DukeInvalidCommandDataInput{
        if(taskInfo.length != expectedInfoNum){
            throw new DukeInvalidCommandDataInput();
        }
    }

    /**
     * Processes a todo task from the user input provided.
     */
    public Task processTodo(String description) {
        return new ToDoTask(description, false);
    }

    /**
     * Processes deletion of a task.
     */
    public boolean processDelete(String delStrIndex, Ui uiPrinter) throws DukeException{
        int delIndex = getExistingIndex(delStrIndex);
        if(delIndex != -1) {
            Task deleteTask = taskList.get(delIndex);
            taskList.remove(delIndex);
            uiPrinter.printMessage(Ui.getDeleteTaskInListMsg(deleteTask, taskList.size()));
            return true;
        }
        else{
            uiPrinter.printMessage("Fail to delete Task!");
            return false;
        }
    }

    /**
     * Check if in put is a valid number.
     */
    public void checkIfNumber(String numStr) throws DukeException {
        try{
            Integer.parseInt(numStr);
        }
        catch(Exception e){
            throw new DukeException(numStr+" is not a number!");
        }
    }

    /**
     * Processes an Event task from the user input provided.
     */
    public Task processEvent(String description, LocalDateTime fromDt, LocalDateTime toDt ) {
        return new EventTask(description, false, fromDt, toDt);
    }

    /**
     * Processes a Deadline task from the user input provided.
     */
    public Task processDeadline(String description, LocalDateTime dt)  {
        return new DeadlineTask(description,false, dt);
    }

    /**
     * Ensures number selected is within the range of values the tasklist has.
     */
    public int getExistingIndex(String findStrIndex) throws DukeException{
        if(taskList.size() == 0){
            throw new DukeException("Task list is empty!");
        }

        checkIfNumber(findStrIndex);

        int findIndex = Integer.parseInt(findStrIndex)-1;
        if(findIndex >= 0 && findIndex < taskList.size()){
            return findIndex;
        }

        throw new DukeException("Invalid task number selected!");
    }
}
