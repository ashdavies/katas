package com.chaos.leaderboard;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.chaos.leaderboard.TestData.driver1;
import static com.chaos.leaderboard.TestData.driver2;
import static com.chaos.leaderboard.TestData.driver3;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderboardTest {

    @Test
    public void itShouldSumThePoints() {
        Map<String, Integer> results = TestData.sampleLeaderboard1.driverResults();

        assertTrue("results " + results, results.containsKey("Lewis Hamilton"));
        assertEquals(18 + 18 + 25, (int) results.get("Lewis Hamilton"));
    }

    @Test
    public void isShouldFindTheWinner() {
        List<String> result = TestData.sampleLeaderboard1.driverRankings();
        assertEquals("Lewis Hamilton", result.get(0));
    }

    @Test
    public void itShouldKeepAllDriversWhenSamePoints() {
        Race winDriver1 = new Race("Australian Grand Prix", driver1, driver2, driver3);
        Race winDriver2 = new Race("Malaysian Grand Prix", driver2, driver1, driver3);
        Leaderboard exEquoLeaderBoard = new Leaderboard(winDriver1, winDriver2);

        List<String> rankings = exEquoLeaderBoard.driverRankings();

        assertEquals(Arrays.asList(driver1.getName(), driver2.getName(), driver3.getName()), rankings);
    }
}