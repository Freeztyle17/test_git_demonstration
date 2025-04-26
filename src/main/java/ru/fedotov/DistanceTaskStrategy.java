package ru.fedotov;

import ru.fedotov.annotation.grid.Aggregate;
import ru.fedotov.annotation.grid.BestSolutionSolver;
import ru.fedotov.annotation.grid.Param;
import ru.fedotov.annotation.grid.Split;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@BestSolutionSolver
public class DistanceTaskStrategy {

    @Split
    public List<TaskBatch> split(
                            @Param("start") BigInteger start,
                            @Param("end") BigInteger end,
                            @Param("parts") int parts) {
        List<TaskBatch> batches = new ArrayList<>();
        BigInteger range = end.subtract(start).divide(BigInteger.valueOf(parts));
        BigInteger currentStart = start;

        for (int i = 0; i < parts; i++) {
            BigInteger currentEnd = (i == parts - 1) ? end : currentStart.add(range);
            batches.add(new TaskBatch(null, currentStart, currentEnd));
            currentStart = currentEnd;
        }

        return batches;
    }

    @Aggregate
    public Map<String, Object> aggregate(@Param("results") List<Map<String, Object>> results) {

        System.out.println("Это изменения для теста гитхаба");

        return results.stream()
                .map(x -> (Map<String, Object>) x.get("result"))
                .filter(result -> result.get("distance") != null)
                .min(Comparator.comparingInt(r -> (int) r.get("distance")))
                .orElseThrow(() -> new RuntimeException("Нет валидных результатов"));
    }
}
