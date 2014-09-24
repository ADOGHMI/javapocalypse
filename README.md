Repository Team Javapocalypse
=============================

Reports
-------

- [TD0 & TD1](./TD1/report.md)
- [TD2](./TD2/report.md)


Git General Usage
-----------------

- general commands : `git status` (current state), `git remote -v` (list of remotes repos),
- _clone_ repository : `git clone git@github.com:Silverwyrda/javapocalypse.git` (pretty much like SVN),
- _staging_ changes : when you change/add some files, they will not be by default included in your next commit : _staging_ means adding them to the _stage_ zone, i.e., include them in your next _commit_, with : `git add <dir/file>`. For example, `git add .` adds every changes in the current directory, based on an exclusion list defined by the file `.gitignore`,
- _commit_ (commits stay local) : `git commit -a -m "My wonderful comment"`,
- _push_ (when you want to send your commits to the server) : `git push origin master` (origin : server alias, as in `git remote -v`), _master_ : main branch.

Working with Branches
---------------------

- `git branch -v` : list _branches_
- `git branch <my-branch>` : create a _branch_ `my-branch`,
- `git checkout <my-branch>` : switch to an existing _branch_ `my-branch` (with `-b` : create then switch to),
- `git merge <my-branch>` : merge _branch_ `my-branch` into the current selected _branch_ (i.e. `master`),
- `git branch -d <my-branch>` : delete a _branch_.

Remote _branches_ syntax : `<server>/<branch>`.

_For a complete tutorial to Git workflows, server operations, internals, etc, please refers to [Git Book](http://git-scm.com/book/)._
