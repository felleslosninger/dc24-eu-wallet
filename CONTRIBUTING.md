# Pull request format

The pull request format can be found in pull_request_format.md.

# Commit messages

Skim though [this](https://cbea.ms/git-commit/) and [this](https://www.freecodecamp.org/news/how-to-write-better-git-commit-messages/) to get an ideo of how to write good commit messages. The guides conflicts on some areas, but each commit should include one of the following:

* feat – a new feature is introduced with the changes
* fix – a bug fix has occurred
* chore – changes that do not relate to a fix or feature and don't modify src or test files (for example updating dependencies)
* refactor – refactored code that neither fixes a bug nor adds a feature
* docs – updates to documentation such as a the README or other markdown files
* style – changes that do not affect the meaning of the code, likely related to code formatting such as white-space, missing semi-colons, and so on.
* test – including new or correcting previous tests
* perf – performance improvements
* ci – continuous integration related
* build – changes that affect the build system or external dependencies
* revert – reverts a previous commit

## Example commit

```
docs: add contributing.md and pull requeqst template
```

# Branches

[Git branching convention](https://dev.to/couchcamote/git-branching-name-convention-cch). 