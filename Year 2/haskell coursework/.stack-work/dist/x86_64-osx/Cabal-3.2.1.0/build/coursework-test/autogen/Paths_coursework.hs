{-# LANGUAGE CPP #-}
{-# LANGUAGE NoRebindableSyntax #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
module Paths_coursework (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []
bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/Users/jeroenmijer/Uni year 2/Lang & Representations/coursework/.stack-work/install/x86_64-osx/cec4838927da0882cddc8ccfbabf3ab7a5b9fc6670fc373bd058665c2e6a05a8/8.10.4/bin"
libdir     = "/Users/jeroenmijer/Uni year 2/Lang & Representations/coursework/.stack-work/install/x86_64-osx/cec4838927da0882cddc8ccfbabf3ab7a5b9fc6670fc373bd058665c2e6a05a8/8.10.4/lib/x86_64-osx-ghc-8.10.4/coursework-0.1.0.0-KVCCq1eimNw7cxVGoD1jPU-coursework-test"
dynlibdir  = "/Users/jeroenmijer/Uni year 2/Lang & Representations/coursework/.stack-work/install/x86_64-osx/cec4838927da0882cddc8ccfbabf3ab7a5b9fc6670fc373bd058665c2e6a05a8/8.10.4/lib/x86_64-osx-ghc-8.10.4"
datadir    = "/Users/jeroenmijer/Uni year 2/Lang & Representations/coursework/.stack-work/install/x86_64-osx/cec4838927da0882cddc8ccfbabf3ab7a5b9fc6670fc373bd058665c2e6a05a8/8.10.4/share/x86_64-osx-ghc-8.10.4/coursework-0.1.0.0"
libexecdir = "/Users/jeroenmijer/Uni year 2/Lang & Representations/coursework/.stack-work/install/x86_64-osx/cec4838927da0882cddc8ccfbabf3ab7a5b9fc6670fc373bd058665c2e6a05a8/8.10.4/libexec/x86_64-osx-ghc-8.10.4/coursework-0.1.0.0"
sysconfdir = "/Users/jeroenmijer/Uni year 2/Lang & Representations/coursework/.stack-work/install/x86_64-osx/cec4838927da0882cddc8ccfbabf3ab7a5b9fc6670fc373bd058665c2e6a05a8/8.10.4/etc"

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "coursework_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "coursework_libdir") (\_ -> return libdir)
getDynLibDir = catchIO (getEnv "coursework_dynlibdir") (\_ -> return dynlibdir)
getDataDir = catchIO (getEnv "coursework_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "coursework_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "coursework_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
