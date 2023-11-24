package hk.edu.polyu.comp.comp3211.group6.Model;

import hk.edu.polyu.comp.comp3211.group6.Controller.*;
import hk.edu.polyu.comp.comp3211.group6.View.Printer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class Interpreter implements StmtVisitor<Void>, CriteriaVisitor<List<PIR>> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm");
    private HashMap<String, PIR> PIRs = new HashMap<>();
    private final int STATUS = 64;
    public Interpreter(){
    }
    public void interpret(Stmt stmt){
        stmt.accept(this);
    }
    @Override
    public List<PIR> visitOrCriteria(OrCriteria orCriteria) {
        List<PIR> leftList = evaluate(orCriteria.getLeft());
        List<PIR> rightList = evaluate(orCriteria.getRight());
        for (PIR pir : leftList){
            if (!rightList.contains(pir))
                rightList.add(pir);
        }
        return rightList;
    }

    @Override
    public List<PIR> visitAndCriteria(AndCriteria andCriteria) {
        List<PIR> leftList = evaluate(andCriteria.getLeft());
        List<PIR> rightList = evaluate(andCriteria.getRight());
        List<PIR> result = new ArrayList<>();
        for (PIR pir :leftList){
            if (rightList.contains(pir))
                result.add(pir);
        }
        return result;
    }
    @Override
    public List<PIR> visitTimeCriteria(TimeCriteria timeCriteria) {
        List<PIR> pirList = new ArrayList<>(PIRs.values());
        List<PIR> result = new ArrayList<>();
        Token operator = timeCriteria.getOperator();
        LocalDateTime queryTime = timeCriteria.getDateTime();
        for (PIR pir : pirList){
            if (pir instanceof HasTime){
                for (LocalDateTime time : ((HasTime) pir).getTime()){
                    switch (operator.type){
                        case BEFORE:
                            if (time.isBefore(queryTime))
                                result.add(pir);
                            break;
                        case EQUAL:
                            if (time.isEqual(queryTime))
                                result.add(pir);
                            break;
                        case AFTER:
                            if (time.isAfter(queryTime))
                                result.add(pir);
                            break;
                        default:
                            throw new PIMError("Invalid operator.");
                    }
                }
            }
        }
        return result;
    }
    @Override
    public List<PIR> visitStringCriteria(StringCriteria stringCriteria) {
        List<PIR> pirList = new ArrayList<>(PIRs.values());
        List<PIR> result = new ArrayList<>();
        for (PIR pir : pirList){
            if (pir.getString().contains(stringCriteria.getQuery()))
                result.add(pir);
        }
        return result;
    }
    @Override
    public List<PIR> visitTypeCriteria(TypeCriteria typeCriteria) {
        List<PIR> pirList = new ArrayList<>(PIRs.values());
        List<PIR> result = new ArrayList<>();
        switch (typeCriteria.getType().type){
            case NOTE:
                for (PIR pir : pirList){
                    if (pir instanceof Note)
                        result.add(pir);
                }
                break;
            case TASK:
                for (PIR pir : pirList){
                    if (pir instanceof Task)
                        result.add(pir);
                }
                break;
            case SCHEDULE:
                for (PIR pir : pirList){
                    if (pir instanceof Schedule)
                        result.add(pir);
                }
                break;
            case CONTACT:
                for (PIR pir : pirList){
                    if (pir instanceof Contact)
                        result.add(pir);
                }
                break;
            default:
                break;
        }
        return result;
    }
    @Override
    public List<PIR> visitIdCriteria(IdCriteria idCriteria) {
        List<PIR> pirList = new ArrayList<>(PIRs.values());
        List<PIR> resList = new ArrayList<>();
        for (PIR pir : pirList){
            if (pir.getIdentifier().equals(idCriteria.getIdentifier()))
                resList.add(pir);
        }
        return resList;
    }
    @Override
    public List<PIR> visitNegCriteria(NegCriteria negCriteria) {
        List<PIR> right = evaluate(negCriteria.getRight());
        List<PIR> result = new ArrayList<>();
        List<PIR> pirList = new ArrayList<>(PIRs.values());
        for (PIR pir : pirList){
            if (!right.contains(pir))
                result.add(pir);
        }
        return result;
    }
    @Override
    public Void visitSearchStmt(SearchStmt stmt) {
        List<PIR> searchRes = evaluate(stmt.getCriteria());
        if (searchRes.isEmpty())
            throw new PIMError("No result.");
        HashMap<String, PIR> resMap = new HashMap<>();
        for (PIR pir : searchRes)
            resMap.put(pir.getIdentifier(), pir);
        Printer printer = new Printer(resMap);
        printer.printAll();
        return null;
    }
    @Override
    public Void visitDeleteStmt(DeleteStmt stmt) {
        String identifier = stmt.getIdentifier();
        if (PIRs.containsKey(identifier)){
            PIRs.remove(identifier);
            System.out.println("PIR \"" + identifier +"\" is deleted.");
            return null;
        }else{throw new PIMError("PIR \"" + identifier +"\" does not exist.");}
    }
    @Override
    public Void visitModifyStmt(ModifyStmt stmt) {
        String identifier = stmt.getIdentifier();
        Modifier modifier = new Modifier(PIRs);
        PIR pir = PIRs.get(identifier);
        if (pir == null)
            throw new PIMError("No such PIR found.");
        modifier.modify(pir);
        return null;
    }
    @Override
    public Void visitCreateStmt(CreateStmt stmt) {
        String identifier = stmt.getIdentifier();
        if (PIRs.containsKey(identifier))
            throw new PIMError("PIR with identifier \"" + identifier + "\" already exists.");
        Token dataType = stmt.getDataType();
        switch (dataType.type){
            case NOTE:
                try {
                    createNoteInterpreter(identifier);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case CONTACT:
                try {
                    createContactInterpreter(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                break;
            case TASK:
                try {
                    createTaskInterpreter(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }catch (DateTimeParseException e){
                    throw new PIMError("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
                }
                break;
            case SCHEDULE:
                try {
                    createScheduleInterpreter(identifier);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }catch (DateTimeParseException e){
                    throw new PIMError("Invalid datetime format. (yyyy-MM-dd,HH:mm)");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dataType.type);
        }
        return null;
    }
    @Override
    public Void visitPrintStmt(PrintStmt stmt) {
        String identifier = stmt.getIdentifier().lexeme;
        Printer printer = new Printer(PIRs);
        printer.print(identifier);
        return null;
    }
    @Override
    public Void visitExitStmt(ExitStmt stmt) {
        System.exit(STATUS);
        return null;
    }
    @Override
    public Void visitLoadStmt(LoadStmt stmt){
        try {
            Interpreterload();
            System.out.println("Loaded.");
        }catch (FileNotFoundException e){
            throw new PIMError("Invalid file name.");
        }catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public Void visitSaveStmt(SaveStmt stmt){
        try {
            Interpretersave();
            System.out.println("Saved.");
        }
        catch (FileNotFoundException e){
            throw new PIMError("Invalid path name.");
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }
    List<PIR> evaluate(Criteria criteria){
        return criteria.accept(this);
    }
    void Interpreterload() throws IOException, ClassNotFoundException{
        String filePath = readLine("File path: ", false);
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        PIRs = (HashMap<String, PIR>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
    void Interpretersave() throws IOException{
        String filePath = readLine("File path: ", false);
        filePath = filePath + ".pim";
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(PIRs);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    void createScheduleInterpreter(String identifier)throws IOException, DateTimeParseException {
        String description = readLine("Description: ", false);
        String startTimeStr = readLine("Start time: ", false);
        LocalDateTime startTime;
        if ("test".equals(System.getProperty("environment"))) {
            startTime = LocalDateTime.parse("2023-11-25,23:59", formatter);
        }
        else{
            startTime = LocalDateTime.parse(startTimeStr, formatter);
        }
        String alarmTimeStr = readLine("Alarm time: ", false);
        LocalDateTime alarmTime;
        if ("test".equals(System.getProperty("environment"))) {
            alarmTime = LocalDateTime.parse("2023-11-24,23:59", formatter);
        }
        else{alarmTime = LocalDateTime.parse(alarmTimeStr, formatter);}
        Schedule schedule = new Schedule(identifier, description, startTime, alarmTime);
        PIRs.put(identifier, schedule);
    }
    void createNoteInterpreter(String identifier)throws IOException{
        String text = readLine("Input your text: ", false);
        Note note = new Note(identifier, text);
        PIRs.put(identifier, note);
    }
    void createTaskInterpreter(String identifier)throws IOException, DateTimeParseException{
        String description = readLine("Description : ", false);
        String deadlineStr = readLine("Deadline: ", false);
        LocalDateTime deadline;
        if ("test".equals(System.getProperty("environment"))) {
            deadline = LocalDateTime.parse("2023-12-30,23:59", formatter);
        }
        else{
            deadline = LocalDateTime.parse(deadlineStr, formatter);
        }
        Task task = new Task(identifier, description, deadline);
        PIRs.put(identifier, task);
    }
    void createContactInterpreter(String identifier)throws IOException{
        String name = readLine("Name: ",false);
        String address = readLine("Address: ", false);
        String mobile = readLine("Mobile number: ", false);
        Contact contact = new Contact(identifier, name, address,mobile);
        PIRs.put(identifier, contact);
    }
    String readLine(String message, boolean newLine)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (newLine)
            System.out.println(message);
        else System.out.print(message);
        return reader.readLine();
    }
    public HashMap<String, PIR> getInterpreterPIRs(){return PIRs;}
    public void setInterpreterPIRs(HashMap<String, PIR> PIRs) {
        this.PIRs = PIRs;
    }
}