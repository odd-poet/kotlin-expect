workflow "New workflow" {
  on = "push"
  resolves = ["Upload artifact"]
}

action "Setup Java Action" {
  uses = "actions/setup-java@232795a7c4c518061ce6a41f418b171de03cb907"
  runs = "./gradlew"
  args = "test"
}

action "Upload artifact" {
  uses = "actions/upload-artifact@ec188c28d23c831d7283eb4280a4490eb5010092"
  needs = ["Setup Java Action"]
}
