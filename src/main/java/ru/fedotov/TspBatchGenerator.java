package ru.fedotov;

import ru.fedotov.annotation.grid.GenerateBatches;
import ru.fedotov.annotation.grid.Param;
import ru.fedotov.annotation.grid.TaskGenerator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@TaskGenerator
public class TspBatchGenerator {

    @GenerateBatches
    public List<TaskBatch> generate(@Param("start") BigInteger start,
                                    @Param("end") BigInteger end) {
        List<TaskBatch> result = new ArrayList<>();
        final BigInteger BATCH_SIZE = BigInteger.valueOf(172800); // такой же размер, как в MasterNode

        BigInteger current = start;

        while (current.compareTo(end) <= 0) {
            BigInteger batchEnd = current.add(BATCH_SIZE).subtract(BigInteger.ONE);
            if (batchEnd.compareTo(end) > 0) {
                batchEnd = end;
            }

            result.add(new TaskBatch(null, current, batchEnd));
            current = batchEnd.add(BigInteger.ONE);
        }

        return result;
    }
}