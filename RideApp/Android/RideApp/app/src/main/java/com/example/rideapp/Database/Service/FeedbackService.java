package com.example.rideapp.Database.Service;

import android.content.Context;
import android.os.AsyncTask;

import com.example.rideapp.Database.DAO.FeedbackDAO;
import com.example.rideapp.Database.DatabaseManager;
import com.example.rideapp.Database.Model.Feedback;

import java.util.List;

public class FeedbackService {
    private static FeedbackDAO feedbackDAO;

    public static class GetFeedbacks extends AsyncTask<Void, Void, List<Feedback>> {
        public GetFeedbacks(Context context) {
            feedbackDAO = DatabaseManager.getInstance(context).getFeedbackDAO();
        }

        @Override
        protected List<Feedback> doInBackground(Void... voids) {
            return feedbackDAO.getFeedbacks();
        }
    }

    public static class Insert extends AsyncTask<Feedback, Void, Feedback> {
        public Insert(Context context) {
            feedbackDAO = DatabaseManager.getInstance(context).getFeedbackDAO();
        }

        @Override
        protected Feedback doInBackground(Feedback...feedbacks) {
            if (feedbacks == null || feedbacks.length != 1) {
                return null;
            }

            Feedback feedback = feedbacks[0];
            long id = feedbackDAO.insert(feedback);
            if (id != -1) {
                feedback.setIdFeed(id);
                return feedback;
            }
            return null;
        }
    }
}
