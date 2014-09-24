Repository Team Javapocalypse
=============================

Git usage
---------

- general usage : `git status` (current state), `git remote -v` (list of remotes repos).

- _clone_ repository : `git clone git@github.com:Silverwyrda/javapocalypse.git` (pretty much like SVN).

- _staging_ changes : when you change/add some files, they will not be by default included in your next commit : _staging_ means adding them to the _stage_ zone, i.e., include them in your next _commit_, with : `git add <dir/file>`. For example, `git add .` adds every changes in the current directory, based on an exclusion list defined by the file `.gitignore`.

- _commit_ (commits stay local) : `git commit -a -m "My wonderful comment"`

- _push_ (when you want to send your commits to the server) : `git push origin master` (origin : server alias, as in `git remote -v`), _master_ : main branch.

_We will see later for branching, merging, etc._
