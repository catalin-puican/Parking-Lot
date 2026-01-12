<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">
    <jsp:useBean id="car" scope="request" type="org.example.parkinglot1.common.CarDto"/>

    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="license-plate">License Plate</label>
                <input type="text" class="form-control" id="license-plate" name="licensePlate" placeholder="" value="${car.licensePlate}" required>
                <div class="invalid-feedback">
                    License plate is required.
                </div>

                <label for="parking-spot">Parking spot</label>
                <input type="text" class="form-control" id="parking-spot" name="parking_spot" placeholder="" value="${car.parkingSpot}" required>
                <div class="invalid-feedback">
                    Parking spot is required.
                </div>

                <label for="owner">Owner</label>
                <select class="form-select" id="user_id" name="user_id" required>
                    <option value="">Select Owner</option>
                    <jsp:useBean id="users" scope="request" type="java.util.List"/>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${car.ownerName eq user.username ? 'selected' : ''}>${user.username}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <hr class="mb-4">

        <input type="hidden" name="car_id" value="${car.id}">
        <button class="btn btn-primary" type="submit">Save</button>

    </form>
</t:pageTemplate>