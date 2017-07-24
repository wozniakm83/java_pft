package pl.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("ba34049c6d8fe29359591cdde7bc9bed4c12efcd");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("wozniakm83", "java_pft")).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
