// Runs the Gradle wrapper from the repo root on any OS.
// Usage: node gradle.mjs <tasks and flags>
import { spawnSync } from "node:child_process";
import { join } from "node:path";

const root = import.meta.dirname;
const windows = process.platform === "win32";
const wrapper = join(root, windows ? "gradlew.bat" : "gradlew");

const result = spawnSync(windows ? `"${wrapper}"` : wrapper, process.argv.slice(2), {
  cwd: root,
  stdio: "inherit",
  // Node can only start .bat files through a shell.
  shell: windows,
});

if (result.error) {
  console.error(result.error.message);
  process.exit(1);
}
process.exit(result.status ?? 1);