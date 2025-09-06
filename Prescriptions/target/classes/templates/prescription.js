
function validateForm() {
    let isValid = true;

    // Clear previous error messages
    document.querySelectorAll('.error-message').forEach(el => el.remove());

    // Validate prescription date
    const prescriptionDate = document.querySelector('input[name="prescriptionDate"]').value;
    if (!prescriptionDate) {
        showError('prescriptionDate', 'Prescription date is required');
        isValid = false;
    }

    // Validate patient name
    const patientName = document.querySelector('input[name="patientName"]').value.trim();
    if (!patientName) {
        showError('patientName', 'Patient name is required');
        isValid = false;
    } else if (patientName.length < 2) {
        showError('patientName', 'Patient name must be at least 2 characters');
        isValid = false;
    }

    // Validate age
    const age = parseInt(document.querySelector('input[name="patientAge"]').value);
    if (!age || age < 0 || age > 150) {
        showError('patientAge', 'Please enter a valid age (0-150)');
        isValid = false;
    }

    // Validate gender
    const gender = document.querySelector('select[name="patientGender"]').value;
    if (!gender) {
        showError('patientGender', 'Please select a gender');
        isValid = false;
    }

    return isValid;
}

function showError(fieldName, message) {
    const field = document.querySelector(`[name="${fieldName}"]`);
    const errorDiv = document.createElement('div');
    errorDiv.className = 'error-message text-danger small';
    errorDiv.textContent = message;
    field.parentNode.appendChild(errorDiv);
    field.classList.add('is-invalid');
}
-->
function confirmDelete(prescriptionId) {
    if (confirm('Are you sure you want to delete this prescription? This action cannot be undone.')) {
        // Create and submit form for deletion
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = `/prescription/delete/${prescriptionId}`;
        document.body.appendChild(form);
        form.submit();
    }
}