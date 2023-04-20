package modules.step.impl;

import modules.dataDefinition.impl.DataDefinitionRegistry;
import modules.dataDefinition.impl.relation.RelationData;
import modules.flow.execution.context.StepExecutionContext;
import modules.step.api.AbstractStepDefinition;
import modules.step.api.DataDefinitionDeclarationImpl;
import modules.step.api.DataNecessity;
import modules.step.api.StepResult;

import javax.management.relation.Relation;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesContentExtractor extends AbstractStepDefinition {
    public FilesContentExtractor() {

        super("Files Content Extractor", true);

        addInput(new DataDefinitionDeclarationImpl("FILES_LIST", DataNecessity.MANDATORY, "Files to extract", DataDefinitionRegistry.LIST));//full path
        addInput(new DataDefinitionDeclarationImpl("LINE", DataNecessity.MANDATORY, "Line number to extract", DataDefinitionRegistry.NUMBER));//full path

        addOutput(new DataDefinitionDeclarationImpl("DATA", DataNecessity.NA, "Files failed to be deleted", DataDefinitionRegistry.RELATION));

    }
    @Override
    public StepResult invoke(StepExecutionContext context) throws IOException {
        List<File> FileList = context.getDataValue("FILES_LIST", List.class);
        int lineNumber = context.getDataValue("LINE", Integer.class);
        List<String> colums = new ArrayList<String>();
        List<String> row = new ArrayList<String>();
        RelationData table= new RelationData(colums);
       if (FileList == null) {
           return StepResult.SUCCESS;
       }
       else {
           colums.add("Serial Number");
           colums.add("Name Of File");
           colums.add("Data in specific line");

           table.setColumns(colums);

           BufferedReader reader = null;
           int index=1;
           for (File specificFile : FileList) {
               //todo add data to row

               reader = new BufferedReader(new FileReader(specificFile));
               String line = null;
               for (int i=0; i <lineNumber;i++){
                   line = reader.readLine();
                   if (i == lineNumber){


                   }

               }
           }
           table.SetNumRowFromString(row);

       }
        return StepResult.SUCCESS;

    }

}
