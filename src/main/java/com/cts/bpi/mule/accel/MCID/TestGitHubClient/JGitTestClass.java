package com.cts.bpi.mule.accel.MCID.TestGitHubClient;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class JGitTestClass {

	private static final String REMOTE_URL = "https://github.com/GirishReddyB/MySpring.git";

	public static void main(String[] args) throws IOException,
			InvalidRemoteException, TransportException, GitAPIException {
		// prepare a new folder for the cloned repository
		File localPath = new File(
				"/Users/girishreddy/Documents/Learn/COG_Learning/Test_6");

		// File.createTempFile("/Users/girishreddy/Documents/Learn/COG_Learning",
		// "");
		/*
		 * boolean del=localPath.delete(); System.out.println("Del : "+del);
		 */

		localPath.deleteOnExit();
		// https://github.com/GirishReddyB/MySpring.git
		// then clone
		System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);

		CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
				"u999", "password");
		// Git result=

		CloneCommand cc = new CloneCommand()
				.setCredentialsProvider(credentialsProvider)
				.setDirectory(localPath).setURI(REMOTE_URL).setBranch("master");
		Git result = cc.call();

		/*
		 * Git.cloneRepository().setCredentialsProvider(credentialsProvider)
		 * .setURI(REMOTE_URL) .setDirectory(localPath) .call();
		 */

		try {
			// Note: the call() returns an opened repository already which needs
			// to be closed to avoid file handle leaks!
			System.out.println("Having repository: "
					+ result.getRepository().getDirectory());
		} finally {
			result.close();
		}
	}

}
