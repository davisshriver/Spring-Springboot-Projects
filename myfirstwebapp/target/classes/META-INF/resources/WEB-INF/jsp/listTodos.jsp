<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<div>
		<h1>Task Manager</h1>
	</div>
	<hr>
	<div>
		<h3>Your Tasks</h3>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Completed?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.description}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.done}</td>
					<td><a href="update-todo?id=${todo.id}" class="btn btn-warning">Update</a></td>
					<td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<a href="add-todo" class="btn btn-success">Add Task</a>
</div>

<%@ include file="common/footer.jspf" %>