package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

@WebServlet(urlPatterns = { "*.do" }, loadOnStartup = 1)
public class BankAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BankAccountService service;

	public BankAccountController() {
		service = new BankAccountServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String path = request.getServletPath();
		System.out.println(path);

		if (path.contentEquals("/addNewBankAccount.do")) {
			String accountHolderName = request.getParameter("customer_name");
			String accountType = request.getParameter("account_type");
			double balance = Double.parseDouble(request.getParameter("account_balance"));

			BankAccount account = new BankAccount(accountType,accountHolderName,  balance);
			if (service.addNewBankAccount(account)) {
				out.println("<h2> Bank Account Created Successfully..</h2> ");
				out.println("<h2> <a href='index.html'> Home </h2>");
				out.close();
			}

		}

		else if (path.contentEquals("/withdraw.do")) {
			long accountId = Long.parseLong(request.getParameter("account_number"));
			double amount = Double.parseDouble(request.getParameter("Amount"));
			
			try {
				double afterWithdraw = service.withdraw(accountId, amount);
				out.println("<h2> New balance after withdraw is Rs: " +afterWithdraw);
			} catch (LowBalanceException e) {
				out.println("<h2> Insufficient Fund </h2> ");
			} catch (BankAccountNotFoundException e) {
				out.println("<h2> BankAccount doesnt exsist </h2> ");
			}
		}
		
		else if (path.contentEquals("/deposit.do")) {
			long accountId = Long.parseLong(request.getParameter("account_number"));
			double amount = Double.parseDouble(request.getParameter("Amount"));
			
			try {
				double afterDeposit = service.deposit(accountId, amount);
				out.println("<h2> New balance after deposit is Rs: " +afterDeposit);
			}
			 catch (BankAccountNotFoundException e) {
				out.println("<h2> BankAccount doesnt exsist </h2> ");
			}
		}
		
		else if (path.contentEquals("/delete.do")) {
			  int accountId = Integer.parseInt(request.getParameter("account_id"));

			  try {
			   if (service.deleteBankAccount(accountId)) {
			    out.println("<h1> Account number with account ID " +accountId + "deleted" );
			    out.close();
			   }
			  } catch (BankAccountNotFoundException e) {
			   out.print("<h2> Accoutn not found <h2>");
			   out.close();
			  }
			  
		}
			  
			  else if (path.contentEquals("/getBalance.do")) {
				  int accountId = Integer.parseInt(request.getParameter("account_no"));

				  try {
				   double balance = service.checkBalance(accountId);

				   out.println("<h1>You have " + balance + " balance in your account!</h1>");
				   out.close();

				  } catch (BankAccountNotFoundException e) {
				   out.print("<h2> Accoutn not found <h2>");
				   out.close();
				  }

				 }
				 

			 else if (path.contentEquals("/transfer.do")) {
				  int amount = Integer.parseInt(request.getParameter("amount"));
				  int fromAccount = Integer.parseInt(request.getParameter("from_account"));
				  int toAccount = Integer.parseInt(request.getParameter("to_account"));

				  try {
				   double withdraw = service.fundTransfer(fromAccount, toAccount, amount);

				   out.println("<h1>New Balance after transfer is " + withdraw + "!</h1>");
				   out.close();

				  } catch (BankAccountNotFoundException e) {
				   out.print("<h2> Accoutn not found <h2>");
				   out.close();

				  } catch (LowBalanceException e) {
				   out.print("<h2> Low balance <h2>");
				   out.close();
				  }

			 }
		
	
		
	}
}
